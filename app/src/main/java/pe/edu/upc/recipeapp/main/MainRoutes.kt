package pe.edu.upc.recipeapp.main

import kotlinx.serialization.Serializable

@Serializable
data object OnboardingRoute

@Serializable
data object MainGraph

@Serializable
data object RecipesRoute

@Serializable
data object ToCookRoute

@Serializable
data class RecipeDetailRoute(val recipeId: Int)
