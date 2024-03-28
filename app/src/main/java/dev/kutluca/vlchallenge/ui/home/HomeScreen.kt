package dev.kutluca.vlchallenge.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import dev.kutluca.vlchallenge.util.compose.AddDisposableObserver

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    AddDisposableObserver(observer = viewModel)

    val characters = remember { viewModel.charactersList }

    Text(text = "Size: ${characters.size}")
}