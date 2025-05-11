package dev.dotingo.receptory

import app.cash.turbine.test
import dev.dotingo.receptory.data.database.dao.CategoryDao
import dev.dotingo.receptory.data.database.entities.CategoryEntity
import dev.dotingo.receptory.data.repository.CategoryRepositoryImpl
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
class CategoryRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var dao: CategoryDao

    private lateinit var repo: CategoryRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repo = CategoryRepositoryImpl(dao)
    }

    @Test
    fun `getAllCategories should emit list from DAO`() = runTest(testDispatcher) {
        val sample = listOf(
            CategoryEntity("1", "Desserts"),
            CategoryEntity("2", "Soups")
        )
        coEvery { dao.getAllCategories() } returns flowOf(sample)

        repo.getAllCategories().test {
            assertEquals(sample, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `insertCategory should delegate to dao`() = runTest(testDispatcher) {
        val category = CategoryEntity("42", "Salads")

        repo.insertCategory(category)

        coVerify(exactly = 1) { dao.insertCategory(category) }
    }

    @Test
    fun `insertAllCategories should delegate to dao`() = runTest(testDispatcher) {
        val categories = listOf(
            CategoryEntity("a", "Beverages"),
            CategoryEntity("b", "Main Course")
        )

        repo.insertAllCategories(categories)

        coVerify(exactly = 1) { dao.insertAllCategories(categories) }
    }

    @Test
    fun `updateCategory should delegate to dao`() = runTest(testDispatcher) {
        val category = CategoryEntity("x", "Appetizers")

        repo.updateCategory(category)

        coVerify(exactly = 1) { dao.updateCategory(category) }
    }

    @Test
    fun `deleteCategory should delegate to dao`() = runTest(testDispatcher) {
        val key = "delete_me"

        repo.deleteCategory(key)

        coVerify(exactly = 1) { dao.deleteCategory(key) }
    }

    @Test
    fun `deleteAllCategories should delegate to dao`() = runTest(testDispatcher) {
        repo.deleteAllCategories()

        coVerify(exactly = 1) { dao.deleteAllCategories() }
    }
}