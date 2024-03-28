package dev.kutluca.vlchallenge.ui.home

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data object Success : HomeUiState
}