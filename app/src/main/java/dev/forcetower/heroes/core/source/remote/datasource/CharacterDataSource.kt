package dev.forcetower.heroes.core.source.remote.datasource

import dev.forcetower.heroes.core.model.dto.asCharacter
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.source.remote.MarvelService
import dev.forcetower.heroes.core.source.local.MarvelDatabase
import kotlinx.coroutines.CoroutineScope

class CharacterDataSource(
    private val service: MarvelService,
    private val database: MarvelDatabase,
    scope: CoroutineScope,
    error: (Throwable) -> Unit
) : SuspendableDataSource<Int, MarvelCharacter>(scope, error) {
    private var offset = 0

    override suspend fun suspendLoadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MarvelCharacter>
    ) {
        val response = service.characters(offset = offset, limit = params.requestedLoadSize)
        val page = response.data
        val items = page.results.map { it.asCharacter() }
        database.characters().insertAll(items)
        callback.onResult(items, 0, page.total, null, params.requestedLoadSize)
    }

    override suspend fun suspendLoadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MarvelCharacter>
    ) {
        val response = service.characters(offset = params.key, limit = params.requestedLoadSize)
        val page = response.data
        val items = page.results.map { it.asCharacter() }
        database.characters().insertAll(items)

        val total = page.total
        val next = params.key + params.requestedLoadSize
        val adjacent = if (next > total) null else next
        callback.onResult(items, adjacent)
    }

    override suspend fun suspendLoadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MarvelCharacter>
    ) {
        val response = service.characters(offset = params.key, limit = params.requestedLoadSize)
        val page = response.data
        val items = page.results.map { it.asCharacter() }
        database.characters().insertAll(items)

        val next = params.key - params.requestedLoadSize
        val adjacent = if (next < 0) null else next
        callback.onResult(items, adjacent)
    }
}