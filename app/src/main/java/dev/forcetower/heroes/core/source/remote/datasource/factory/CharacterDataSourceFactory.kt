package dev.forcetower.heroes.core.source.remote.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.source.remote.MarvelService
import dev.forcetower.heroes.core.source.remote.datasource.CharacterDataSource
import dev.forcetower.heroes.core.source.local.MarvelDatabase
import kotlinx.coroutines.CoroutineScope

class CharacterDataSourceFactory(
    private val service: MarvelService,
    private val database: MarvelDatabase,
    private val scope: CoroutineScope,
    private val error: (Throwable) -> Unit
) : DataSource.Factory<Int, MarvelCharacter>() {
    val sourceLiveData = MutableLiveData<CharacterDataSource>()

    override fun create(): DataSource<Int, MarvelCharacter> {
        val source = CharacterDataSource(service, database, scope, error)
        sourceLiveData.postValue(source)
        return source
    }
}