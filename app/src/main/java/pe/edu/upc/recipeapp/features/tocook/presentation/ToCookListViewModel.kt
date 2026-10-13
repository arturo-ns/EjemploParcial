package pe.edu.upc.recipeapp.features.tocook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.recipeapp.features.recipes.domain.Recipe
import pe.edu.upc.recipeapp.features.tocook.domain.ToCookRepository
import javax.inject.Inject

@HiltViewModel
class ToCookListViewModel @Inject constructor(
    private val toCookRepository: ToCookRepository
) : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>?>(null)
    val recipes: StateFlow<List<Recipe>?> = _recipes.asStateFlow()

    init {
        viewModelScope.launch {
            toCookRepository.observeAll().collect { savedRecipes ->
                _recipes.value = savedRecipes
            }
        }
    }

    fun remove(recipeId: Int) {
        viewModelScope.launch {
            toCookRepository.remove(recipeId)
        }
    }
}
