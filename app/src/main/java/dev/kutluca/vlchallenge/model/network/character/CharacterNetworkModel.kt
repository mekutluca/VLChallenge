package dev.kutluca.vlchallenge.model.network.character

import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel

data class CharacterNetworkModel(
    val name: String,
    val status: String,
    val location: LocationNetworkModel,
    val image: String,
) {
    fun toUiModel(): CharacterUiModel = CharacterUiModel(
        name = name,
        status = status,
        location = location.name,
        imageUrl = image,
    )
}

data class LocationNetworkModel(
    val name: String,
)