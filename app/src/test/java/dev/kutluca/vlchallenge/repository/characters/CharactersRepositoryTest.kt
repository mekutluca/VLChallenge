package dev.kutluca.vlchallenge.repository.characters

import dev.kutluca.vlchallenge.gateway.characters.CharactersGateway
import dev.kutluca.vlchallenge.model.network.character.CharacterNetworkModel
import dev.kutluca.vlchallenge.model.network.character.LocationNetworkModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CharactersRepositoryTest {

    private lateinit var charactersRepository: CharactersRepository

    private val charactersGateway = mockk<CharactersGateway>(relaxed = true)

    private val charactersList = listOf(
        CharacterNetworkModel(
            name = "Rick",
            status = "Alive",
            location = LocationNetworkModel(name = "Earth"),
            image = ""
        )
    )

    @Before
    fun setup() {
        charactersRepository = CharactersRepositoryImpl(charactersGateway)
    }

    @Test
    fun `When characters list requested expect characters gateway called`() = runTest {
        charactersRepository.getCharacters()

        coVerify {
            charactersGateway.fetchCharacters()
        }
    }

    @Test(expected = IOException::class)
    fun `When gateway throws error expect error thrown`() = runTest {
        coEvery {
            charactersGateway.fetchCharacters()
        } throws IOException("Error while fetching characters.")

        charactersRepository.getCharacters()
    }

}