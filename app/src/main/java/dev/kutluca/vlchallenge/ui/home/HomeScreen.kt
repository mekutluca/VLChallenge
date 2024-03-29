package dev.kutluca.vlchallenge.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import dev.kutluca.vlchallenge.util.compose.AddDisposableObserver
import dev.kutluca.vlchallenge.util.compose.swipable

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
    Column {
        Text(text = "Size: ${characters.size}")

        characters.take(3).forEach {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .padding(24.dp)
                    .swipable(
                        onSwipedLeft = onCharacterSwipedLeft,
                        onSwipedRight = onCharacterSwipedRight,
                    )
                    .border(width = 4.dp, color = Color.Red)
            ) {
                Text(text = it.name)
            }

        }
    }
}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}