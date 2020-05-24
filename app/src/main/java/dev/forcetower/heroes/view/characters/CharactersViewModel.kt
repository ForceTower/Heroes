package dev.forcetower.heroes.view.characters

import androidx.lifecycle.*
import androidx.paging.PagedList
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.source.repository.MarvelRepository
import dev.forcetower.heroes.core.ui.Event
import dev.forcetower.heroes.core.source.remote.datasource.helpers.Status
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    repository: MarvelRepository
) : ViewModel(), CharacterActions {
    private val characterSource = repository.characters(viewModelScope) { onError(it) }
    private val _errorState = MutableLiveData<Event<Throwable>>()
    private val _onCharacterSelected = MutableLiveData<Event<MarvelCharacter>>()

    override val characters: LiveData<PagedList<MarvelCharacter>> = characterSource.pagedList
    override val networkState = characterSource.networkState
    override val refreshing = characterSource.refreshState.map { it.status == Status.RUNNING }
    override val errorState: LiveData<Event<Throwable>> = _errorState

    val onCharacterSelected: LiveData<Event<MarvelCharacter>> = _onCharacterSelected

    private fun onError(throwable: Throwable) {
        _errorState.value = Event(throwable)
    }

    override fun retry() {
        characterSource.retry.invoke()
    }

    override fun refresh() {
        characterSource.refresh.invoke()
    }

    override fun onCharacterSelected(character: MarvelCharacter) {
        _onCharacterSelected.value = Event(character)
    }
}