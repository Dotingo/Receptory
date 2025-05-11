package dev.dotingo.receptory

import app.cash.turbine.test
import dev.dotingo.receptory.data.database.dao.RecipeDao
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.data.repository.RecipeRepositoryImpl
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
class RecipeRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var dao: RecipeDao

    private lateinit var repo: RecipeRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repo = RecipeRepositoryImpl(dao)
    }

    @Test
    fun `getAllRecipes should emit list from DAO`() = runTest(testDispatcher) {
        // given
        val sample = listOf(
            RecipeEntity("1", "Pancakes", "Mix and fry"),
            RecipeEntity("2", "Omelette", "Beat eggs")
        )
        coEvery { dao.getAllRecipes() } returns flowOf(sample)

        // when
        repo.getAllRecipes().test {
            // then
            assertEquals(sample, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `insertRecipe should delegate to dao`() = runTest(testDispatcher) {
        val recipe = RecipeEntity("42", "Salad", "Chop veggies")

        repo.insertRecipe(recipe)

        coVerify(exactly = 1) { dao.insertRecipe(recipe) }
    }

    @Test
    fun `insertAllRecipes should delegate to dao`() = runTest(testDispatcher) {
        val recipes = listOf(
            RecipeEntity("a", "Soup", "Boil water"),
            RecipeEntity("b", "Stew", "Simmer meat")
        )

        repo.insertAllRecipes(recipes)

        coVerify(exactly = 1) { dao.insertAllRecipes(recipes) }
    }

    @Test
    fun `updateRecipe should delegate to dao`() = runTest(testDispatcher) {
        val recipe = RecipeEntity("x", "Cake", "Bake at 180°C")

        repo.updateRecipe(recipe)

        coVerify(exactly = 1) { dao.updateRecipe(recipe) }
    }

    @Test
    fun `deleteRecipe should delegate to dao`() = runTest(testDispatcher) {
        val key = "delete_me"

        repo.deleteRecipe(key)

        coVerify(exactly = 1) { dao.deleteRecipe(key) }
    }

    @Test
    fun `deleteAllRecipes should delegate to dao`() = runTest(testDispatcher) {
        repo.deleteAllRecipes()

        coVerify(exactly = 1) { dao.deleteAllRecipes() }
    }

    @Test
    fun `getRecipeByKey should return value from dao`() = runTest(testDispatcher) {
        val entity = RecipeEntity("k1", "Pizza", "Bake dough")
        coEvery { dao.getRecipeByKey("k1") } returns entity

        val result = repo.getRecipeByKey("k1")

        assertEquals(entity, result)
    }
}