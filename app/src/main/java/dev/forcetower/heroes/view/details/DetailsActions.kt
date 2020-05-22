package dev.forcetower.heroes.view.details

import androidx.lifecycle.LiveData
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.ui.Event

interface DetailsActions {
    val character: LiveData<MarvelCharacter>
    val onNavigateBack: LiveData<Event<Unit>>
    val onNavigateToMostExpensive: LiveData<Event<Unit>>

    fun setCharacterId(id: Int)
    fun onNavigateBack()
    fun onNavigateToMostExpensive()
}