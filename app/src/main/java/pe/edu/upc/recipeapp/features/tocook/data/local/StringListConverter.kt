package pe.edu.upc.recipeapp.features.tocook.data.local

import androidx.room3.ColumnTypeConverter

/**
 * Persists [List] of strings in one TEXT column.
 * The unit separator does not appear in the recipe text returned by the API.
 */
object StringListConverter {
    private const val SEPARATOR = "\u001F"

    @ColumnTypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(SEPARATOR)
    }

    @ColumnTypeConverter
    fun toStringList(value: String): List<String> {
        if (value.isEmpty()) {
            return emptyList()
        }
        return value.split(SEPARATOR)
    }
}
