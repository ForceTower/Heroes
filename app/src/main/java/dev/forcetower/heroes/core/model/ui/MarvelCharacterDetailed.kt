package dev.forcetower.heroes.core.model.ui

import androidx.room.Embedded
import androidx.room.Relation
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.persistence.MarvelComic

data class MarvelCharacterDetailed(
    @Embedded
    val character: MarvelCharacter,
    @Relation(entity = MarvelComic::class, parentColumn = "id", entityColumn = "characterId")
    val comics: List<MarvelComicDetailed>
)