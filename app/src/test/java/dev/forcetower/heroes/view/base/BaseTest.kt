package dev.forcetower.heroes.view.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.forcetower.heroes.CoroutineTestRule
import dev.forcetower.heroes.core.source.local.FakeMarvelDatabase
import dev.forcetower.heroes.core.source.local.MarvelDatabase
import dev.forcetower.heroes.core.source.remote.MarvelService
import dev.forcetower.heroes.extensions.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {
    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    internal val database: MarvelDatabase = FakeMarvelDatabase()
    internal val service: MarvelService = mock()
}