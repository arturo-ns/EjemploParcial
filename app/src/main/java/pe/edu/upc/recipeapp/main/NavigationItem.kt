package pe.edu.upc.recipeapp.main

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import pe.edu.upc.recipeapp.R
import pe.edu.upc.recipeapp.core.designsystems.recipes
import pe.edu.upc.recipeapp.core.designsystems.toCook

enum class NavigationItem(
    val route: @Serializable Any,
    val icon: ImageVector,
    @StringRes val titleRes: Int
) {
    HOME(
        route = HomeRoute,
        icon = recipes,
        titleRes = R.string.tab_recipes
    ),
    TO_COOK(
        route = ToCookRoute,
        icon = toCook,
        titleRes = R.string.tab_to_cook
    )
}
