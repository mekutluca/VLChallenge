package dev.kutluca.vlchallenge.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import dev.kutluca.vlchallenge.ui.theme.component.character.CharacterCard
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
            onCharacterSwipedLeft = viewModel::onCharacterSwipedLeft,
            onCharacterSwipedRight = viewModel::onCharacterSwipedRight,
        )
    }
}

@Composable
private fun CharactersContent(
    characters: SnapshotStateList<CharacterUiModel>,
    onCharacterSwipedLeft: () -> Unit,
    onCharacterSwipedRight: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "You've seen all characters! We'll add more at the next season.",
            textAlign = TextAlign.Center,
        )
    }

    characters.asReversed().forEach {
        CharacterCard(
            character = it,
            onCharacterSwipedLeft = onCharacterSwipedLeft,
            onCharacterSwipedRight = onCharacterSwipedRight
        )
    }
}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}