package pe.edu.upc.recipeapp.features.recipes.data

import pe.edu.upc.recipeapp.features.recipes.data.remote.RecipeApi
import pe.edu.upc.recipeapp.features.recipes.data.remote.toDomain
import pe.edu.upc.recipeapp.features.recipes.domain.Recipe
import pe.edu.upc.recipeapp.features.recipes.domain.RecipeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi
) : RecipeRepository {
    override suspend fun getRecipes(): Result<List<Recipe>> {
        return runCatching {
            api.getRecipes().results.map { it.toDomain() }
        }
    }

    override suspend fun getRecipeById(id: Int): Result<Recipe> {
        return runCatching {
            api.getRecipeById(id).toDomain()
        }
    }
}
