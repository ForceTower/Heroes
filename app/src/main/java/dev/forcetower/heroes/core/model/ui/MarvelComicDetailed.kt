package dev.forcetower.heroes.core.model.ui

import androidx.room.Embedded
import androidx.room.Relation
import dev.forcetower.heroes.core.model.persistence.MarvelComic
import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice

data class MarvelComicDetailed(
    @Embedded
    val comic: MarvelComic,
    @Relation(parentColumn = "id", entityColumn = "comicId")
    val prices: List<MarvelComicPrice>
)