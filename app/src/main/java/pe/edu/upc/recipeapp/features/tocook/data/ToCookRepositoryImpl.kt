package pe.edu.upc.recipeapp.features.tocook.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pe.edu.upc.recipeapp.features.recipes.domain.Recipe
import pe.edu.upc.recipeapp.features.tocook.data.local.ToCookDao
import pe.edu.upc.recipeapp.features.tocook.data.local.toDomain
import pe.edu.upc.recipeapp.features.tocook.data.local.toEntity
import pe.edu.upc.recipeapp.features.tocook.domain.ToCookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToCookRepositoryImpl @Inject constructor(
    private val dao: ToCookDao
) : ToCookRepository {
    override fun observeAll(): Flow<List<Recipe>> {
        return dao.observeAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun observeIsSaved(recipeId: Int): Flow<Boolean> {
        return dao.observeById(recipeId).map { entity ->
            entity != null
        }
    }

    override suspend fun findById(recipeId: Int): Recipe? {
        return dao.findById(recipeId)?.toDomain()
    }

    override suspend fun save(recipe: Recipe) {
        dao.insert(recipe.toEntity())
    }

    override suspend fun remove(recipeId: Int) {
        dao.deleteById(recipeId)
    }
}
