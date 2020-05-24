package dev.forcetower.heroes.view.expensive

import androidx.lifecycle.Observer
import dev.forcetower.heroes.core.model.MarvelFakeDataFactory
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import dev.forcetower.heroes.core.source.repository.MarvelRepository
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
class ExpensiveViewModelTest : BaseTest() {
    @Captor
    private lateinit var captor: ArgumentCaptor<MarvelExpensiveComic?>
    private val observer: Observer<MarvelExpensiveComic?> = mock()

    private lateinit var viewModel: ExpensiveViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val repository = MarvelRepository(service, database)
        viewModel = ExpensiveViewModel(repository)
    }

    /**
     * Result always comes from database from a database query...
     * so as long as the query is right, everything is fine
     */
    @Test
    fun fetchMostExpensive() = runBlockingTest {
        `when`(service.comics(1, 0, 100))
            .thenReturn(MarvelFakeDataFactory.makeComicsResponse())

        viewModel.comic.observeForever(observer)
        viewModel.setCharacterId(1)

        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(MarvelFakeDataFactory.makeMarvelExpensiveComic(), value)
        }
    }
}