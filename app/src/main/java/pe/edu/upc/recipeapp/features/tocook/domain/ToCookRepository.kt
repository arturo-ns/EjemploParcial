package pe.edu.upc.recipeapp.features.tocook.domain

import kotlinx.coroutines.flow.Flow
import pe.edu.upc.recipeapp.features.home.domain.Recipe

/**
 * Local catalog of recipes the user marked to cook.
 * Implementations must persist changes so they survive process death.
 */
interface ToCookRepository {
    fun observeAll(): Flow<List<Recipe>>

    fun observeIsSaved(recipeId: Int): Flow<Boolean>

    suspend fun findById(recipeId: Int): Recipe?

    suspend fun save(recipe: Recipe)

    suspend fun remove(recipeId: Int)
}
