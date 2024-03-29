package dev.kutluca.vlchallenge.gateway.characters


import dev.kutluca.vlchallenge.data.network.CharactersApi
import dev.kutluca.vlchallenge.model.network.character.CharacterNetworkModel
import dev.kutluca.vlchallenge.model.network.character.LocationNetworkModel
import dev.kutluca.vlchallenge.model.network.response.character.CharactersApiInfo
import dev.kutluca.vlchallenge.model.network.response.character.CharactersNetworkResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CharactersGatewayTest {

    private lateinit var charactersGateway: CharactersGateway

    private val charactersApi = mockk<CharactersApi>(relaxed = true)

    private val charactersList = listOf(
        CharacterNetworkModel(
            name = "Rick",
            status = "Alive",
            location = LocationNetworkModel(name = "Earth"),
            image = ""
        )
    )
    private val charactersResponse = CharactersNetworkResponse(
        info = CharactersApiInfo(count = 1, pages = 1, next = null, prev = null),
        results = charactersList
    )

    @Before
    fun setup() {
        charactersGateway = CharactersGatewayImpl(charactersApi)
    }

    @Test
    fun `When fetch characters requested expect api called`() = runTest {
        charactersGateway.fetchCharacters()

        coVerify {
            charactersApi.fetchCharacters()
        }
    }

    @Test(expected = IOException::class)
    fun `When api throws error expect error thrown`() = runTest {
        coEvery {
            charactersApi.fetchCharacters()
        } throws IOException("Error while fetching characters.")

        charactersGateway.fetchCharacters()
    }

    @Test
    fun `When api returns successfully expect list returned`() = runTest {
        coEvery {
            charactersApi.fetchCharacters()
        } returns charactersResponse

        val list = charactersGateway.fetchCharacters()

        assertEquals(charactersList, list)
    }
}