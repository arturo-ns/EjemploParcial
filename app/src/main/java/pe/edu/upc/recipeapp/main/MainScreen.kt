package pe.edu.upc.recipeapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.recipeapp.features.onboarding.presentation.OnboardingScreen
import pe.edu.upc.recipeapp.features.recipes.presentation.RecipeDetailScreen
import pe.edu.upc.recipeapp.features.recipes.presentation.RecipeListScreen
import pe.edu.upc.recipeapp.features.tocook.presentation.ToCookListScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry?.destination?.hierarchy?.any { destination ->
        destination.hasRoute<RecipesRoute>() || destination.hasRoute<ToCookRoute>()
    } == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                MainNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = OnboardingRoute,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable<OnboardingRoute> {
                OnboardingScreen(
                    onContinue = {
                        navController.navigate(MainGraph) {
                            // Remove onboarding so the system back action cannot return to it.
                            popUpTo<OnboardingRoute> { inclusive = true }
                        }
                    }
                )
            }
            navigation<MainGraph>(startDestination = RecipesRoute) {
                composable<RecipesRoute> {
                    RecipeListScreen(
                        onRecipeSelected = { recipeId ->
                            navController.navigate(RecipeDetailRoute(recipeId))
                        }
                    )
                }
                composable<ToCookRoute> {
                    ToCookListScreen()
                }
                composable<RecipeDetailRoute> {
                    RecipeDetailScreen(
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
