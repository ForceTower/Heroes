package dev.forcetower.heroes.view.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.repository.MarvelRepository
import dev.forcetower.heroes.core.ui.Event
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    repository: MarvelRepository
) : ViewModel() {
    private val characterSource = repository.characters(viewModelScope) { onError(it) }
    private val _errorState = MutableLiveData<Event<Throwable>>()

    val characters: LiveData<PagedList<MarvelCharacter>> = characterSource.pagedList
    val refreshState = characterSource.refreshState
    val networkState = characterSource.networkState
    val errorState: LiveData<Event<Throwable>> = _errorState

    private fun onError(throwable: Throwable) {
        _errorState.value = Event(throwable)
    }

    fun retry() {
        characterSource.retry.invoke()
    }

    fun refresh() {
        characterSource.refresh.invoke()
    }
}