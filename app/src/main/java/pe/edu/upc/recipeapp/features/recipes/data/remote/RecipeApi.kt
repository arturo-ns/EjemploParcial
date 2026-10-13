package pe.edu.upc.recipeapp.features.recipes.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {
    @GET("api/recipes")
    suspend fun getRecipes(): RecipesResponse

    @GET("api/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): RecipeDto
}
