package dev.kutluca.vlchallenge.gateway.characters

import dev.kutluca.vlchallenge.data.network.CharactersApi
import dev.kutluca.vlchallenge.model.network.character.CharacterNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharactersGateway {
    /**
     * Fetch characters list from the api endpoint.
     * @param page - Page number that we want to retrieve characters from.
     *
     * @return List of fetched Characters.
     */
    suspend fun fetchCharacters(page: Int = 1): List<CharacterNetworkModel>
}

class CharactersGatewayImpl @Inject constructor(
    private val charactersApi: CharactersApi,
) : CharactersGateway {

    override suspend fun fetchCharacters(page: Int): List<CharacterNetworkModel> {
        return withContext(Dispatchers.IO) {
            charactersApi.fetchCharacters(page = page)
        }.results
    }
}