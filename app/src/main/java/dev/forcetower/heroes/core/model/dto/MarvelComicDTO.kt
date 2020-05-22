package dev.forcetower.heroes.core.model.dto

import dev.forcetower.heroes.core.model.persistence.MarvelComic
import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice

data class MarvelComicDTO(
    val id: Int,
    val digitalId: Int,
    val issueNumber: Int,
    val title: String,
    val description: String,
    val prices: List<MarvelComicPrice>,
    val thumbnail: MarvelThumbnailDTO
)

fun MarvelComicDTO.asComic(character: Int): MarvelComic {
    return MarvelComic(id, digitalId, issueNumber, title, description, "${thumbnail.path}.${thumbnail.extension}", character)
}