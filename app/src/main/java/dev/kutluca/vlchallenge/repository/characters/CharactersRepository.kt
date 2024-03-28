package dev.kutluca.vlchallenge.repository.characters

import dev.kutluca.vlchallenge.gateway.characters.CharactersGateway
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import javax.inject.Inject

interface CharactersRepository {

    suspend fun getCharacters(): List<CharacterUiModel>

}

class CharactersRepositoryImpl @Inject constructor(
    private val charactersGateway: CharactersGateway,
) : CharactersRepository {

    private var currentPage = 1

    override suspend fun getCharacters(): List<CharacterUiModel> {
        return runCatching {
            charactersGateway.fetchCharacters(currentPage)
                .map { it.toUiModel() }
        }.onSuccess {
            currentPage += 1
        }.getOrElse {
            throw it
        }
    }
}