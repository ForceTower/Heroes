package dev.forcetower.heroes.view.expensive

import androidx.lifecycle.LiveData
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import dev.forcetower.heroes.core.ui.Event

interface ExpensiveActions {
    val loading: LiveData<Boolean>
    val comic: LiveData<MarvelExpensiveComic?>
    val error: LiveData<Event<Int>>

    fun setCharacterId(id: Int)
    fun onNavigateBack()
}