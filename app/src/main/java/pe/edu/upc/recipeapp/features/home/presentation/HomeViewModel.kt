package pe.edu.upc.recipeapp.features.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.recipeapp.features.home.domain.Recipe
import pe.edu.upc.recipeapp.features.home.domain.RecipeRepository
import javax.inject.Inject

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(val recipes: List<Recipe>) : HomeUiState

    data object Error : HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            recipeRepository.getRecipes()
                .onSuccess { recipes ->
                    _uiState.value = HomeUiState.Success(recipes)
                }
                .onFailure { error ->
                    Log.e(TAG, "Unable to load recipes", error)
                    _uiState.value = HomeUiState.Error
                }
        }
    }

    private companion object {
        const val TAG = "HomeViewModel"
    }
}
