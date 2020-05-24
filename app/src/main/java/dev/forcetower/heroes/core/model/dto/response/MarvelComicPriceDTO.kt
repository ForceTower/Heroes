package dev.forcetower.heroes.core.model.dto.response

import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice

data class MarvelComicPriceDTO(
    val type: String,
    val price: Float
)

fun MarvelComicPriceDTO.asPrice(comicId: Int): MarvelComicPrice {
    return MarvelComicPrice(comicId, type, price)
}