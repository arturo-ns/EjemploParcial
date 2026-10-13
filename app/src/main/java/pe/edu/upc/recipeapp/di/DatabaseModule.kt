package pe.edu.upc.recipeapp.di

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import pe.edu.upc.recipeapp.features.tocook.data.local.RecipeDatabase
import pe.edu.upc.recipeapp.features.tocook.data.local.ToCookDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRecipeDatabase(
        @ApplicationContext context: Context
    ): RecipeDatabase {
        return Room.databaseBuilder<RecipeDatabase>(
            context = context,
            name = "to_cook.db"
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    @Provides
    fun provideToCookDao(database: RecipeDatabase): ToCookDao {
        return database.toCookDao()
    }
}
