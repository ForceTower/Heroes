package dev.forcetower.heroes.core.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.room.withTransaction
import dev.forcetower.heroes.core.model.dto.MarvelComicDTO
import dev.forcetower.heroes.core.model.dto.asComic
import dev.forcetower.heroes.core.model.dto.response.GeneralResponse
import dev.forcetower.heroes.core.model.dto.response.asPrice
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import dev.forcetower.heroes.core.source.MarvelSource
import dev.forcetower.heroes.core.source.remote.MarvelService
import dev.forcetower.heroes.core.source.remote.datasource.factory.CharacterDataSourceFactory
import dev.forcetower.heroes.core.source.remote.datasource.helpers.Listing
import dev.forcetower.heroes.core.source.local.MarvelDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val service: MarvelService,
    private val database: MarvelDatabase
) : MarvelSource {
    override fun characters(
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

    override fun character(characterId: Int): LiveData<MarvelCharacter?> {
        return database.characters().character(characterId).asLiveData()
    }

    override fun fetchAllComics(
        characterId: Int,
        error: (Throwable) -> Unit
    ): LiveData<Pair<Int, Int>> = liveData(Dispatchers.IO) {
        try {
            var offset = 0
            var total = 0
            emit(0 to 1)
            do {
                Timber.d("Iteration...")
                // Here we interested on fetching the max amount of comics in a single run,
                // so we limit to 100 at each iteration
                val response = service.comics(characterId, offset, 100)
                saveResponse(response, characterId)
                if (total == 0) total = response.data.total
                offset += response.data.count
                emit(offset to total)
            } while (offset < total)
        } catch (throwable: Throwable) {
            Timber.e(throwable, "error")
            withContext(Dispatchers.Main) {
                error(throwable)
            }
            emit(-1 to -1)
        }
    }

    override fun mostExpensiveComic(
        characterId: Int
    ): LiveData<MarvelExpensiveComic?> {
        return database.comics().mostExpensive(characterId).asLiveData()
    }

    private suspend fun saveResponse(response: GeneralResponse<MarvelComicDTO>, characterId: Int) {
        val results = response.data.results
        val comics = results.map { it.asComic(characterId) }
        val prices = results.map { it.prices.map { el -> el.asPrice(it.id) } }.flatten()
        database.withTransaction {
            database.comics().insertAll(comics)
            database.prices().insertAll(prices)
        }
    }
}