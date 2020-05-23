package dev.forcetower.heroes.core.source

import androidx.lifecycle.LiveData
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import dev.forcetower.heroes.core.source.remote.datasource.helpers.Listing
import kotlinx.coroutines.CoroutineScope

interface MarvelSource {
    fun characters(scope: CoroutineScope, error: (Throwable) -> Unit): Listing<MarvelCharacter>
    fun character(characterId: Int): LiveData<MarvelCharacter?>
    fun fetchAllComics(characterId: Int, error: (Throwable) -> Unit): LiveData<Pair<Int, Int>>
    fun mostExpensiveComic(characterId: Int): LiveData<MarvelExpensiveComic?>
}