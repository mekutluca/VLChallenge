package dev.kutluca.vlchallenge.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.kutluca.vlchallenge.util.compose.AddDisposableObserver

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    AddDisposableObserver(observer = viewModel)
}