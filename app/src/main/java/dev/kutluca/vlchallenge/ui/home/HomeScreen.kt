package dev.kutluca.vlchallenge.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import dev.kutluca.vlchallenge.util.compose.AddDisposableObserver

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    AddDisposableObserver(observer = viewModel)

    val characters = remember { viewModel.charactersList }

    when (viewModel.uiState) {
        HomeUiState.Loading -> LoadingContent()
        is HomeUiState.Success -> CharactersContent(
            characters = characters,
            onCharacterSwiped = viewModel::onCharacterPopped,
        )
    }
}

@Composable
private fun CharactersContent(
    characters: SnapshotStateList<CharacterUiModel>,
    onCharacterSwiped: () -> Unit
) {
    Column {
        Text(text = "Size: ${characters.size}")

        Text(text = "Current char: ${characters.firstOrNull()?.name}")

        Button(onClick = onCharacterSwiped) {
            Text(text = "Pop 5")
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}