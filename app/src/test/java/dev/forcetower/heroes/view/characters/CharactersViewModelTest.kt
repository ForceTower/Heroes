package dev.forcetower.heroes.view.characters

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import dev.forcetower.heroes.core.model.MarvelFakeDataFactory
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.source.repository.MarvelRepository
import dev.forcetower.heroes.core.ui.Event
import dev.forcetower.heroes.extensions.mock
import dev.forcetower.heroes.view.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
class CharactersViewModelTest : BaseTest() {
    @Captor
    private lateinit var captor: ArgumentCaptor<PagedList<MarvelCharacter>>
    @Captor
    private lateinit var errorCaptor: ArgumentCaptor<Event<Throwable>>

    private val observer: Observer<PagedList<MarvelCharacter>> = mock()
    private val errorObserver: Observer<Event<Throwable>> = mock()

    private lateinit var viewModel: CharactersViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val repository = MarvelRepository(service, database)
        viewModel = CharactersViewModel(repository)
    }

    @Test
    fun fetchDataCorrectly() = runBlockingTest {
        val response = MarvelFakeDataFactory.makeCharactersResponse()
        `when`(service.characters(0, 20)).thenReturn(response)

        viewModel.errorState.observeForever(errorObserver)
        viewModel.characters.observeForever(observer)

        Thread.sleep(100)

        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(value.size, 20)
        }

        errorCaptor.run {
            verify(errorObserver, times(0)).onChanged(capture())
        }
    }
}