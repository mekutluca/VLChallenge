package dev.kutluca.vlchallenge.gateway.characters

import dev.kutluca.vlchallenge.data.network.CharactersApi
import dev.kutluca.vlchallenge.model.network.response.character.CharactersNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharactersGateway {

    suspend fun fetchCharacters(page: Int = 1): CharactersNetworkResponse
}

class CharactersGatewayImpl @Inject constructor(
    private val charactersApi: CharactersApi,
) : CharactersGateway {

    override suspend fun fetchCharacters(page: Int): CharactersNetworkResponse {
        return withContext(Dispatchers.IO) {
            charactersApi.fetchCharacters(page = page)
        }
    }
}