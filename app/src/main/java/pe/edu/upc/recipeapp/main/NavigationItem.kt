package pe.edu.upc.recipeapp.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import pe.edu.upc.recipeapp.R

enum class NavigationItem(
    val route: @Serializable Any,
    val icon: ImageVector,
    @StringRes val titleRes: Int
) {
    HOME(
        route = HomeRoute,
        icon = Icons.Filled.RestaurantMenu,
        titleRes = R.string.tab_recipes
    ),
    TO_COOK(
        route = ToCookRoute,
        icon = Icons.Filled.SoupKitchen,
        titleRes = R.string.tab_to_cook
    )
}
