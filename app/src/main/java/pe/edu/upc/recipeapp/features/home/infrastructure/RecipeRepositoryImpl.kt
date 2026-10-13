package pe.edu.upc.recipeapp.features.home.infrastructure

import pe.edu.upc.recipeapp.features.home.infrastructure.remote.RecipeApi
import pe.edu.upc.recipeapp.features.home.infrastructure.remote.toDomain
import pe.edu.upc.recipeapp.features.home.domain.Recipe
import pe.edu.upc.recipeapp.features.home.domain.RecipeRepository
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
