package dev.kutluca.vlchallenge.ui.home

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kutluca.vlchallenge.repository.characters.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel(), DefaultLifecycleObserver {

}