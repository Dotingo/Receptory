package dev.dotingo.receptory

import android.app.Application
import app.cash.turbine.test
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.dotingo.receptory.data.database.entities.CategoryEntity
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.domain.repository.CategoryRepository
import dev.dotingo.receptory.domain.repository.RecipeRepository
import dev.dotingo.receptory.presentation.screens.main_screen.MainScreenViewModel
import dev.dotingo.receptory.presentation.screens.main_screen.SortType
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

// Заглушка для FakeRecipeRepository
class FakeRecipeRepository(private val recipes: List<RecipeEntity>) : RecipeRepository {
    override fun getAllRecipes() = flowOf(recipes)
    override suspend fun getRecipeByKey(key: String): RecipeEntity? =
        recipes.find { it.recipeId == key }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAllRecipes(recipes: List<RecipeEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipe(recipe: RecipeEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipeKey: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllRecipes() {
        TODO("Not yet implemented")
    }
}

// Заглушка для CategoryRepository
class FakeCategoryRepository : CategoryRepository {
    override fun getAllCategories() = flowOf(emptyList<CategoryEntity>())
    override suspend fun insertCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAllCategories(categories: List<CategoryEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCategories() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategory(categoryId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainScreenViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainScreenViewModel

    private val sampleRecipes = listOf(
        RecipeEntity(
            "1",
            title = "Banana Bread",
            creationTime = 100L,
            rating = 0,
            kcal = "300",
            favorite = false,
            category = "Dessert",
            imageUrl = ""
        ),
        RecipeEntity(
            "2",
            title = "Apple Pie",
            creationTime = 200L,
            rating = 3,
            kcal = "250",
            favorite = true,
            category = "Dessert",
            imageUrl = ""
        ),
        RecipeEntity(
            "3",
            title = "Carrot Soup",
            creationTime = 150L,
            rating = 4,
            kcal = "150",
            favorite = false,
            category = "Soup",
            imageUrl = ""
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        val fakeRecipeRepo = FakeRecipeRepository(sampleRecipes)
        val fakeCategoryRepo = FakeCategoryRepository()

        val mockApp = mockk<Application>(relaxed = true)
        val mockFfs = mockk<FirebaseFirestore>(relaxed = true)
        val mockAuth = mockk<FirebaseAuth>(relaxed = true)

        viewModel = MainScreenViewModel(
            application = mockApp,
            recipeRepository = fakeRecipeRepo,
            categoryRepository = fakeCategoryRepo,
            firebaseFirestore = mockFfs,
            auth = mockAuth
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `sort by date ascending`() = runTest {
        viewModel.changeDescending(true)
        viewModel.setSortType(SortType.DATE)
        viewModel.fetchAllRecipes()

        viewModel.recipesList.test {
            val sorted = awaitItem().map { it.recipeId }
            assertEquals(listOf("1", "3", "2"), sorted)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sort by date descending`() = runTest {
        viewModel.changeDescending(false)
        viewModel.setSortType(SortType.DATE)
        viewModel.fetchAllRecipes()

        viewModel.recipesList.test {
            val sorted = awaitItem().map { it.recipeId }
            // 200,150,100 → 2,3,1
            assertEquals(listOf("2", "3", "1"), sorted)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sort by name ascending`() = runTest {
        viewModel.changeDescending(true)
        viewModel.setSortType(SortType.NAME)
        viewModel.fetchAllRecipes()

        viewModel.recipesList.test {
            val sorted = awaitItem().map { it.title }
            // Alphabetical: Apple Pie, Banana Bread, Carrot Soup
            assertEquals(listOf("Apple Pie", "Banana Bread", "Carrot Soup"), sorted)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sort by calories descending`() = runTest {
        viewModel.changeDescending(true)
        viewModel.setSortType(SortType.CALORIES)
        viewModel.fetchAllRecipes()

        viewModel.recipesList.test {
            val sorted = awaitItem().map { it.kcal }
            // kcal: 300,250,150
            assertEquals(listOf("300", "250", "150"), sorted)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sort by rating ascending`() = runTest {
        viewModel.changeDescending(false)
        viewModel.setSortType(SortType.RATING)
        viewModel.fetchAllRecipes()

        viewModel.recipesList.test {
            val sorted = awaitItem().map { it.rating }
            // rating: 0, 3, 4
            assertEquals(listOf(0, 3, 4), sorted)
            cancelAndIgnoreRemainingEvents()
        }
    }
}