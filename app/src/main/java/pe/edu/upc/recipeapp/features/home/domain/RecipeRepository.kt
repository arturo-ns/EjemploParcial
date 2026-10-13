package pe.edu.upc.recipeapp.features.home.domain

interface RecipeRepository {
    suspend fun getRecipes(): Result<List<Recipe>>

    suspend fun getRecipeById(id: Int): Result<Recipe>
}
