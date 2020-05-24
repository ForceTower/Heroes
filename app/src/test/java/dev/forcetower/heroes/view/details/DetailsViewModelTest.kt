package dev.forcetower.heroes.view.details

import androidx.lifecycle.Observer
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.source.repository.MarvelRepository
import dev.forcetower.heroes.extensions.mock
import dev.forcetower.heroes.view.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
class DetailsViewModelTest : BaseTest() {
    @Captor
    private lateinit var captor: ArgumentCaptor<MarvelCharacter?>
    private val observer: Observer<MarvelCharacter?> = mock()

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val repository = MarvelRepository(service, database)
        viewModel = DetailsViewModel(repository)
    }

    /**
     * Data comes from local database... it will never fail...
     * unless there's a development error
     */
    @Test
    fun loadSuccessAndNeverFails() = runBlockingTest {
        viewModel.setCharacterId(1)
        viewModel.character.observeForever(observer)
        captor.run {
            verify(observer, atLeastOnce()).onChanged(capture())
            assertNotNull(value)
        }
    }
}