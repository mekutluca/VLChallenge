package dev.kutluca.vlchallenge.ui.home

import androidx.lifecycle.LifecycleOwner
import dev.kutluca.vlchallenge.model.presentation.character.CharacterStatus
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import dev.kutluca.vlchallenge.repository.characters.CharactersRepository
import dev.kutluca.vlchallenge.util.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: HomeViewModel

    private val charactersRepository = mockk<CharactersRepository>(relaxed = true)
    private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)

    private val charactersList = listOf(
        CharacterUiModel(
            name = "Rick", status = CharacterStatus.ALIVE, location = "Earth", imageUrl = "",
        ),
        CharacterUiModel(
            name = "Rick", status = CharacterStatus.ALIVE, location = "Earth", imageUrl = "",
        ),
        CharacterUiModel(
            name = "Rick", status = CharacterStatus.ALIVE, location = "Earth", imageUrl = "",
        ),
        CharacterUiModel(
            name = "Rick", status = CharacterStatus.ALIVE, location = "Earth", imageUrl = "",
        ),
        CharacterUiModel(
            name = "Rick", status = CharacterStatus.ALIVE, location = "Earth", imageUrl = "",
        ),
        CharacterUiModel(
            name = "Rick", status = CharacterStatus.ALIVE, location = "Earth", imageUrl = "",
        ),
    )

    @Before
    fun setup() {
        viewModel = HomeViewModel(charactersRepository)
    }

    @Test
    fun `When view model initialized expect ui state as loading`() {
        assertEquals(HomeUiState.Loading, viewModel.uiState)
    }

    @Test
    fun `When view started expect characters fetched`() = runTest {
        viewModel.onStart(lifecycleOwner)

        coVerify {
            charactersRepository.getCharacters()
        }
    }

    @Test
    fun `When view started and characters fetched successfully expect list updated`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } returns charactersList

        viewModel.onStart(lifecycleOwner)

        assertEquals(viewModel.charactersList.toList(), charactersList)
    }

    @Test
    fun `When view started but characters threw error expect list is empty`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } throws IOException("Network error")

        viewModel.onStart(lifecycleOwner)

        assertTrue(viewModel.charactersList.isEmpty())
        assertEquals(viewModel.uiState, HomeUiState.Success)
    }

    @Test
    fun `When character swiped left expect first character removed`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } returns charactersList

        viewModel.onStart(lifecycleOwner)

        assertEquals(6, viewModel.charactersList.size)

        viewModel.onCharacterSwipedLeft()
        assertEquals(5, viewModel.charactersList.size)
    }

    @Test
    fun `When character swiped and current size less than five expect new batch fetched`() =
        runTest {
            coEvery {
                charactersRepository.getCharacters()
            } returns charactersList

            viewModel.onStart(lifecycleOwner)

            viewModel.onCharacterSwipedLeft()
            viewModel.onCharacterSwipedLeft()

            // One on start, one after swipe
            coVerify(exactly = 2) {
                charactersRepository.getCharacters()
            }
        }
}