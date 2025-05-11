package dev.dotingo.receptory

import app.cash.turbine.test
import dev.dotingo.receptory.data.database.dao.ShoppingItemDao
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.repository.ShoppingItemRepositoryImpl
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
class ShoppingItemRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var dao: ShoppingItemDao

    private lateinit var repo: ShoppingItemRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repo = ShoppingItemRepositoryImpl(dao)
    }

    @Test
    fun `insertShoppingItem should return dao result`() = runTest(testDispatcher) {
        val item = ShoppingItemEntity(0L, shoppingListId = 123L, name = "Milk", isPurchased = true)
        coEvery { dao.insert(item) } returns 42L

        val result = repo.insertShoppingItem(item)

        assertEquals(42L, result)
        coVerify(exactly = 1) { dao.insert(item) }
    }

    @Test
    fun `insertAllShoppingItems should return dao results`() = runTest(testDispatcher) {
        val items = listOf(
            ShoppingItemEntity(0L, 1L, "Bread", true),
            ShoppingItemEntity(0L, 1L, "Eggs", false)
        )
        val ids = listOf(10L, 11L)
        coEvery { dao.insertAll(items) } returns ids

        val result = repo.insertAllShoppingItems(items)

        assertEquals(ids, result)
        coVerify(exactly = 1) { dao.insertAll(items) }
    }

    @Test
    fun `updateShoppingItem should delegate to dao`() = runTest(testDispatcher) {
        val item = ShoppingItemEntity(5L, 1L, "Butter", false)

        repo.updateShoppingItem(item)

        coVerify(exactly = 1) { dao.update(item) }
    }

    @Test
    fun `deleteShoppingItem should delegate to dao`() = runTest(testDispatcher) {
        val item = ShoppingItemEntity(6L, 1L, "Cheese", false)

        repo.deleteShoppingItem(item)

        coVerify(exactly = 1) { dao.delete(item) }
    }

    @Test
    fun `getItemsForShoppingListFlow should emit list from dao`() = runTest(testDispatcher) {
        val listId = 99L
        val sample = listOf(
            ShoppingItemEntity(1L, listId, "Apple", true),
            ShoppingItemEntity(2L, listId, "Banana", false)
        )
        coEvery { dao.getItemsForShoppingListFlow(listId) } returns flowOf(sample)

        repo.getItemsForShoppingListFlow(listId).test {
            assertEquals(sample, awaitItem())
            awaitComplete()
        }
    }
}