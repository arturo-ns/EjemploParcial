package pe.edu.upc.recipeapp.features.tocook.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToCookDao {
    @Query("SELECT * FROM to_cook_recipes ORDER BY name COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<ToCookRecipeEntity>>

    @Query("SELECT * FROM to_cook_recipes WHERE id = :id LIMIT 1")
    fun observeById(id: Int): Flow<ToCookRecipeEntity?>

    @Query("SELECT * FROM to_cook_recipes WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): ToCookRecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ToCookRecipeEntity)

    @Query("DELETE FROM to_cook_recipes WHERE id = :id")
    suspend fun deleteById(id: Int)
}
