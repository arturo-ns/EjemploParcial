package pe.edu.upc.recipeapp.features.tocook.data.local

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [ToCookRecipeEntity::class],
    version = 1,
    exportSchema = false
)
@ColumnTypeConverters(StringListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun toCookDao(): ToCookDao
}
