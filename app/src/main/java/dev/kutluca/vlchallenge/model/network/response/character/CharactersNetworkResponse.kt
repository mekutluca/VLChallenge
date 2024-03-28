package dev.kutluca.vlchallenge.model.network.response.character

import dev.kutluca.vlchallenge.model.network.character.CharacterNetworkModel

data class CharactersNetworkResponse(
    val info: CharactersApiInfo,
    val results: List<CharacterNetworkModel>,
)

data class CharactersApiInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
