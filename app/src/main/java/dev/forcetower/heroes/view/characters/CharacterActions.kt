package dev.forcetower.heroes.view.characters

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.source.remote.datasource.helpers.NetworkState
import dev.forcetower.heroes.core.ui.Event

interface CharacterActions {
    val characters: LiveData<PagedList<MarvelCharacter>>
    val errorState: LiveData<Event<Throwable>>
    val refreshing: LiveData<Boolean>
    val networkState: LiveData<NetworkState>

    fun retry()
    fun refresh()
    fun onCharacterSelected(character: MarvelCharacter)
}