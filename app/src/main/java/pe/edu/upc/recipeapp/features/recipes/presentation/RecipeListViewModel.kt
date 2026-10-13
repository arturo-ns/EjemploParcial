package pe.edu.upc.recipeapp.features.recipes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.recipeapp.features.recipes.domain.Recipe
import pe.edu.upc.recipeapp.features.recipes.domain.RecipeRepository
import javax.inject.Inject

sealed interface RecipeListUiState {
    data object Loading : RecipeListUiState

    data class Success(val recipes: List<Recipe>) : RecipeListUiState

    data object Error : RecipeListUiState
}

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RecipeListUiState>(RecipeListUiState.Loading)
    val uiState: StateFlow<RecipeListUiState> = _uiState.asStateFlow()

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            _uiState.value = RecipeListUiState.Loading
            recipeRepository.getRecipes()
                .onSuccess { recipes ->
                    _uiState.value = RecipeListUiState.Success(recipes)
                }
                .onFailure { error ->
                    Log.e(TAG, "Unable to load recipes", error)
                    _uiState.value = RecipeListUiState.Error
                }
        }
    }

    private companion object {
        const val TAG = "RecipeListViewModel"
    }
}
