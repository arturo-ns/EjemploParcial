package pe.edu.upc.recipeapp.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import pe.edu.upc.recipeapp.R
import pe.edu.upc.recipeapp.features.home.domain.Recipe
import pe.edu.upc.recipeapp.features.home.presentation.components.DifficultyBadge
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.action_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.hasError || uiState.recipe == null -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.error_detail),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = viewModel::loadRecipe) {
                        Text(text = stringResource(R.string.action_retry))
                    }
                }
            }

            else -> {
                RecipeDetailContent(
                    recipe = uiState.recipe!!,
                    isToCook = uiState.isToCook,
                    isUpdating = uiState.isUpdating,
                    onToggle = viewModel::toggleToCook,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun RecipeDetailContent(
    recipe: Recipe,
    isToCook: Boolean,
    isUpdating: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = recipe.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.cuisine_label, recipe.cuisine),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.difficulty_label),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                DifficultyBadge(difficulty = recipe.difficulty)
            }
            Text(
                text = stringResource(R.string.prep_time, recipe.prepTimeMinutes),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.cook_time, recipe.cookTimeMinutes),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.servings, recipe.servings),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(
                    R.string.rating,
                    String.format(Locale.US, "%.1f", recipe.rating)
                ),
                style = MaterialTheme.typography.bodyLarge
            )
            ToCookToggle(
                isToCook = isToCook,
                isUpdating = isUpdating,
                onToggle = onToggle
            )
            HorizontalDivider()
            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleMedium
            )
            recipe.ingredients.forEach { ingredient ->
                Text(
                    text = "• $ingredient",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            HorizontalDivider()
            Text(
                text = stringResource(R.string.instructions),
                style = MaterialTheme.typography.titleMedium
            )
            recipe.instructions.forEachIndexed { index, instruction ->
                Text(
                    text = "${index + 1}. $instruction",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun ToCookToggle(
    isToCook: Boolean,
    isUpdating: Boolean,
    onToggle: () -> Unit
) {
    val status = if (isToCook) {
        stringResource(R.string.marked_to_cook)
    } else {
        stringResource(R.string.not_marked_to_cook)
    }
    Text(
        text = status,
        style = MaterialTheme.typography.labelLarge,
        color = if (isToCook) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    )
    if (isToCook) {
        Button(
            onClick = onToggle,
            enabled = !isUpdating,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Bookmark,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.unmark_to_cook),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    } else {
        OutlinedButton(
            onClick = onToggle,
            enabled = !isUpdating,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.mark_to_cook),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
