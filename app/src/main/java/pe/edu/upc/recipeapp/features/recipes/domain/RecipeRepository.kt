package pe.edu.upc.recipeapp.features.recipes.domain

interface RecipeRepository {
    suspend fun getRecipes(): Result<List<Recipe>>

    suspend fun getRecipeById(id: Int): Result<Recipe>
}
