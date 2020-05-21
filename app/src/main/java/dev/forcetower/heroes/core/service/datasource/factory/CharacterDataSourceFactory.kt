package dev.forcetower.heroes.core.service.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.service.MarvelService
import dev.forcetower.heroes.core.service.datasource.CharacterDataSource
import kotlinx.coroutines.CoroutineScope

class CharacterDataSourceFactory(
    private val service: MarvelService,
    private val scope: CoroutineScope,
    private val error: (Throwable) -> Unit
) : DataSource.Factory<Int, MarvelCharacter>() {
    val sourceLiveData = MutableLiveData<CharacterDataSource>()

    override fun create(): DataSource<Int, MarvelCharacter> {
        val source = CharacterDataSource(service, scope, error)
        sourceLiveData.postValue(source)
        return source
    }
}