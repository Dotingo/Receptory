package dev.dotingo.receptory

import app.cash.turbine.test
import dev.dotingo.receptory.data.database.dao.ShoppingListDao
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListWithItems
import dev.dotingo.receptory.data.repository.ShoppingListRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShoppingListRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var dao: ShoppingListDao

    private lateinit var repo: ShoppingListRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repo = ShoppingListRepositoryImpl(dao)
    }

    @Test
    fun `insertShoppingList should return dao result`() = runTest(testDispatcher) {
        val list = ShoppingListEntity(0L, name = "Groceries")
        coEvery { dao.insert(list) } returns 77L

        val result = repo.insertShoppingList(list)

        assertEquals(77L, result)
        coVerify(exactly = 1) { dao.insert(list) }
    }

    @Test
    fun `updateShoppingList should delegate to dao`() = runTest(testDispatcher) {
        val list = ShoppingListEntity(5L, name = "Weekend")

        repo.updateShoppingList(list)

        coVerify(exactly = 1) { dao.update(list) }
    }

    @Test
    fun `deleteShoppingList should delegate to dao`() = runTest(testDispatcher) {
        val list = ShoppingListEntity(9L, name = "Party")

        repo.deleteShoppingList(list)

        coVerify(exactly = 1) { dao.delete(list) }
    }

    @Test
    fun `getAllShoppingListsWithItemsFlow should emit list from dao`() = runTest(testDispatcher) {
        val sample = listOf(
            ShoppingListWithItems(
                shoppingList = ShoppingListEntity(1L, "A"),
                items = listOf(
                    ShoppingItemEntity(1L, 1L, "X"),
                    ShoppingItemEntity(2L, 1L, "Y", true)
                )
            ),
            ShoppingListWithItems(
                shoppingList = ShoppingListEntity(2L, "B"),
                items = emptyList()
            )
        )
        coEvery { dao.getAllShoppingListsWithItemsFlow() } returns flowOf(sample)

        repo.getAllShoppingListsWithItemsFlow().test {
            assertEquals(sample, awaitItem())
            awaitComplete()
        }
    }
}