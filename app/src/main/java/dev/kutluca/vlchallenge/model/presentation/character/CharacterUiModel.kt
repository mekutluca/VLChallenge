package dev.kutluca.vlchallenge.model.presentation.character

data class CharacterUiModel(
    val name: String,
    val status: CharacterStatus,
    val location: String,
    val imageUrl: String,
)

enum class CharacterStatus(val text: String) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown"),
}
