package dev.forcetower.heroes.view.expensive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dev.forcetower.heroes.R
import dev.forcetower.heroes.core.extensions.setValueIfNew
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import dev.forcetower.heroes.core.source.repository.MarvelRepository
import dev.forcetower.heroes.core.ui.Event
import javax.inject.Inject

class ExpensiveViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel(), ExpensiveActions {
    private val _characterId = MutableLiveData<Int>()
    private val _loading = MediatorLiveData<Boolean>()
    private val _error = MediatorLiveData<Event<Int>>()
    private val _onNavigateBack = MutableLiveData<Event<Unit>>()

    override val loading: LiveData<Boolean> = _loading
    override val error: LiveData<Event<Int>> = _error
    override val comic: LiveData<MarvelExpensiveComic?> = _characterId.switchMap {
        repository.mostExpensiveComic(it)
    }

    val onNavigateBack: LiveData<Event<Unit>> = _onNavigateBack

    init {
        _loading.addSource(_characterId) { id ->
            val fetchSrc = repository.fetchAllComics(id) { error -> onError(error) }
            _loading.addSource(fetchSrc) { pair ->
                val result = pair.first < pair.second
                _loading.setValueIfNew(result)
            }
        }
    }

    override fun setCharacterId(id: Int) {
        _characterId.setValueIfNew(id)
    }

    override fun onNavigateBack() {
        _onNavigateBack.value = Event(Unit)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onError(error: Throwable) {
        if (comic.value == null) {
            // In this case, nothing was loaded, show a more aggressive error
            _error.value = Event(R.string.failed_load_comic)
        } else {
            _error.value = Event(R.string.failed_load_comic_partial)
        }
    }
}