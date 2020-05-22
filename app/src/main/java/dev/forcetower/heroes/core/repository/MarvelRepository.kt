package dev.forcetower.heroes.core.repository

import androidx.lifecycle.switchMap
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.service.MarvelService
import dev.forcetower.heroes.core.service.datasource.factory.CharacterDataSourceFactory
import dev.forcetower.heroes.core.service.datasource.helpers.Listing
import dev.forcetower.heroes.core.storage.MarvelDatabase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val service: MarvelService,
    private val database: MarvelDatabase
) {
    fun characters(
        scope: CoroutineScope,
        error: (Throwable) -> Unit
    ): Listing<MarvelCharacter> {
        val factory = CharacterDataSourceFactory(service, database, scope, error)
        val livePagedList = LivePagedListBuilder(factory, Config(
            pageSize = 20,
            initialLoadSizeHint = 20,
            enablePlaceholders = false
        )).build()

        val refreshState = factory.sourceLiveData.switchMap { it.initialLoad }

        return Listing(
            pagedList = livePagedList,
            networkState = factory.sourceLiveData.switchMap { it.networkState },
            retry = { factory.sourceLiveData.value?.retryAllFailed() },
            refresh = { factory.sourceLiveData.value?.invalidate() },
            refreshState = refreshState
        )
    }
}