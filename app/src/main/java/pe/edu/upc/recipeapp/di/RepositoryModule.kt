package pe.edu.upc.recipeapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.recipeapp.features.recipes.data.RecipeRepositoryImpl
import pe.edu.upc.recipeapp.features.recipes.domain.RecipeRepository
import pe.edu.upc.recipeapp.features.tocook.data.ToCookRepositoryImpl
import pe.edu.upc.recipeapp.features.tocook.domain.ToCookRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    @Singleton
    abstract fun bindToCookRepository(impl: ToCookRepositoryImpl): ToCookRepository
}
