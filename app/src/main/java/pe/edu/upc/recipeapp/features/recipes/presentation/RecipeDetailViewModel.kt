package pe.edu.upc.recipeapp.features.recipes.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.edu.upc.recipeapp.features.recipes.domain.Recipe
import pe.edu.upc.recipeapp.features.recipes.domain.RecipeRepository
import pe.edu.upc.recipeapp.features.tocook.domain.ToCookRepository
import pe.edu.upc.recipeapp.main.RecipeDetailRoute
import javax.inject.Inject

data class RecipeDetailUiState(
    val isLoading: Boolean = true,
    val recipe: Recipe? = null,
    val isToCook: Boolean = false,
    val isUpdating: Boolean = false,
    val hasError: Boolean = false
)

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
    private val toCookRepository: ToCookRepository
) : ViewModel() {
    private val recipeId: Int = savedStateHandle.toRoute<RecipeDetailRoute>().recipeId

    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState: StateFlow<RecipeDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            toCookRepository.observeIsSaved(recipeId).collect { isSaved ->
                _uiState.update { it.copy(isToCook = isSaved) }
            }
        }
        loadRecipe()
    }

    fun loadRecipe() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hasError = false) }
            val recipe = recipeRepository.getRecipeById(recipeId).getOrElse { error ->
                Log.e(TAG, "Unable to load recipe $recipeId", error)
                null
            } ?: toCookRepository.findById(recipeId)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    recipe = recipe,
                    hasError = recipe == null
                )
            }
        }
    }

    fun toggleToCook() {
        val recipe = _uiState.value.recipe ?: return
        val isSaved = _uiState.value.isToCook
        viewModelScope.launch {
            _uiState.update { it.copy(isUpdating = true) }
            if (isSaved) {
                toCookRepository.remove(recipe.id)
            } else {
                toCookRepository.save(recipe)
            }
            _uiState.update { it.copy(isUpdating = false) }
        }
    }

    private companion object {
        const val TAG = "RecipeDetailViewModel"
    }
}
