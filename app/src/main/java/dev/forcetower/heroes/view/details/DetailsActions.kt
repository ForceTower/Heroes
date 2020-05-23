package dev.forcetower.heroes.view.details

import androidx.lifecycle.LiveData
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter

interface DetailsActions {
    val character: LiveData<MarvelCharacter?>

    fun setCharacterId(id: Int)
    fun onNavigateBack()
    fun onNavigateToMostExpensive()
}