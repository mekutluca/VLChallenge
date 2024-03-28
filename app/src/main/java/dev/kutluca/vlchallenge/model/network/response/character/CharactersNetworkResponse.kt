package dev.kutluca.vlchallenge.model.network.response.character

data class CharactersNetworkResponse(
    val info: CharactersApiInfo,
    val results: List<CharactersNetworkResponse>,
)

data class CharactersApiInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
