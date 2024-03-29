package dev.kutluca.vlchallenge.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import dev.kutluca.vlchallenge.repository.characters.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel(), DefaultLifecycleObserver {

    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Loading)
    val charactersList = mutableStateListOf<CharacterUiModel>()

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        updateList()
    }

    fun onCharacterSwipedLeft() {
        // We can handle the rejection but it is out of scope for the challenge.
        charactersList.removeFirstOrNull()
        populateCharactersList()
    }

    fun onCharacterSwipedRight() {
        // We can handle the like but it is out of scope for the challenge.
        charactersList.removeFirstOrNull()
        populateCharactersList()
    }

    private fun updateList() {
        viewModelScope.launch {
            runCatching {
                charactersRepository.getCharacters()
            }.onSuccess {
                charactersList.addAll(it)
            }.also {
                uiState = HomeUiState.Success
            }
        }
    }

    private fun populateCharactersList() {
        if (charactersList.size < 5) {
            updateList()
        }
    }
}