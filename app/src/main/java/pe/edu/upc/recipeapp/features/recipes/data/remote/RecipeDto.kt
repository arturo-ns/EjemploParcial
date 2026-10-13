package pe.edu.upc.recipeapp.features.recipes.data.remote

import pe.edu.upc.recipeapp.features.recipes.domain.Recipe

data class RecipesResponse(
    val count: Int,
    val results: List<RecipeDto>
)

data class RecipeDto(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val difficulty: String,
    val cuisine: String,
    val image: String,
    val rating: Double
)

fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = id,
        name = name,
        ingredients = ingredients,
        instructions = instructions,
        prepTimeMinutes = prepTimeMinutes,
        cookTimeMinutes = cookTimeMinutes,
        servings = servings,
        difficulty = difficulty,
        cuisine = cuisine,
        image = image,
        rating = rating
    )
}
