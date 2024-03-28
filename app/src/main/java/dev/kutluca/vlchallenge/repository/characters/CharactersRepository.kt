package dev.kutluca.vlchallenge.repository.characters

import dev.kutluca.vlchallenge.gateway.characters.CharactersGateway
import javax.inject.Inject

interface CharactersRepository {

}

class CharactersRepositoryImpl @Inject constructor(
    private val charactersGateway: CharactersGateway,
) : CharactersRepository {

}