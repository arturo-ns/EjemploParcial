package pe.edu.upc.recipeapp

import org.junit.Assert.assertEquals
import org.junit.Test
import pe.edu.upc.recipeapp.features.home.infrastructure.remote.RecipeDto
import pe.edu.upc.recipeapp.features.home.infrastructure.remote.toDomain
import pe.edu.upc.recipeapp.features.tocook.data.local.StringListConverter

class RecipeDtoMapperTest {
    @Test
    fun toDomainMapsRequiredFields() {
        val dto = RecipeDto(
            id = 1,
            name = "Classic Margherita Pizza",
            ingredients = listOf("Pizza dough", "Tomato sauce"),
            instructions = listOf("Bake"),
            prepTimeMinutes = 20,
            cookTimeMinutes = 15,
            servings = 4,
            difficulty = "Easy",
            cuisine = "Italian",
            image = "https://example.com/1.webp",
            rating = 4.6
        )

        val recipe = dto.toDomain()

        assertEquals(1, recipe.id)
        assertEquals("Classic Margherita Pizza", recipe.name)
        assertEquals("Italian", recipe.cuisine)
        assertEquals("Easy", recipe.difficulty)
        assertEquals(20, recipe.prepTimeMinutes)
        assertEquals(15, recipe.cookTimeMinutes)
        assertEquals(4, recipe.servings)
        assertEquals(4.6, recipe.rating, 0.001)
        assertEquals(listOf("Pizza dough", "Tomato sauce"), recipe.ingredients)
        assertEquals(listOf("Bake"), recipe.instructions)
    }

    @Test
    fun stringListConverterRoundTripsValues() {
        val original = listOf("Tomato sauce", "Fresh basil")
        val stored = StringListConverter.fromStringList(original)
        assertEquals(original, StringListConverter.toStringList(stored))
        assertEquals(emptyList<String>(), StringListConverter.toStringList(""))
    }
}
