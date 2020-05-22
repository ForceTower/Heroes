package dev.forcetower.heroes.view.characters

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.service.datasource.helpers.NetworkState
import dev.forcetower.heroes.core.ui.Event

interface CharacterActions {
    val characters: LiveData<PagedList<MarvelCharacter>>
    val errorState: LiveData<Event<Throwable>>
    val refreshing: LiveData<Boolean>
    val networkState: LiveData<NetworkState>
    val onCharacterSelected: LiveData<Event<MarvelCharacter>>

    fun retry()
    fun refresh()
    fun onCharacterSelected(character: MarvelCharacter)
}