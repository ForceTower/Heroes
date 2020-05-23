package dev.forcetower.heroes.core.model.dto

import dev.forcetower.heroes.core.model.dto.response.MarvelComicPriceDTO
import dev.forcetower.heroes.core.model.persistence.MarvelComic

data class MarvelComicDTO(
    val id: Int,
    val digitalId: Int,
    val issueNumber: Float,
    val title: String,
    val description: String?,
    val prices: List<MarvelComicPriceDTO>,
    val thumbnail: MarvelThumbnailDTO
)

fun MarvelComicDTO.asComic(character: Int): MarvelComic {
    return MarvelComic(id, digitalId, issueNumber, title, description ?: "", "${thumbnail.path}.${thumbnail.extension}", character)
}