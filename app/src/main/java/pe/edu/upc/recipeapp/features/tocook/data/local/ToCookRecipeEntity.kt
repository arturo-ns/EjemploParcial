package pe.edu.upc.recipeapp.features.tocook.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import pe.edu.upc.recipeapp.features.recipes.domain.Recipe

@Entity(tableName = "to_cook_recipes")
data class ToCookRecipeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val cuisine: String,
    val difficulty: String,
    val image: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val rating: Double,
    val ingredients: List<String>,
    val instructions: List<String>
)

fun ToCookRecipeEntity.toDomain(): Recipe {
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

fun Recipe.toEntity(): ToCookRecipeEntity {
    return ToCookRecipeEntity(
        id = id,
        name = name,
        cuisine = cuisine,
        difficulty = difficulty,
        image = image,
        prepTimeMinutes = prepTimeMinutes,
        cookTimeMinutes = cookTimeMinutes,
        servings = servings,
        rating = rating,
        ingredients = ingredients,
        instructions = instructions
    )
}
