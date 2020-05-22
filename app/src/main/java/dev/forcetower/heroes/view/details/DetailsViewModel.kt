package dev.forcetower.heroes.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dev.forcetower.heroes.core.extensions.setValueIfNew
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.repository.MarvelRepository
import dev.forcetower.heroes.core.ui.Event
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel(), DetailsActions {
    private val _characterId = MutableLiveData<Int>()
    private val _onNavigateBack = MutableLiveData<Event<Unit>>()
    private val _onNavigateToMostExpensive = MutableLiveData<Event<Unit>>()

    override val onNavigateBack: LiveData<Event<Unit>> = _onNavigateBack
    override val onNavigateToMostExpensive: LiveData<Event<Unit>> = _onNavigateToMostExpensive
    override val character: LiveData<MarvelCharacter> = _characterId.switchMap {
        repository.character(it)
    }

    override fun setCharacterId(id: Int) {
        _characterId.setValueIfNew(id)
    }

    override fun onNavigateBack() {
    }

    override fun onNavigateToMostExpensive() {
    }
}