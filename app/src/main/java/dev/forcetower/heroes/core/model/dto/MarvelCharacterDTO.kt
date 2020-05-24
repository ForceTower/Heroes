package dev.forcetower.heroes.core.model.dto

import dev.forcetower.heroes.core.model.persistence.MarvelCharacter

data class MarvelCharacterDTO(
    val id: Int,
    val name: String,
    val description: String,
    // TODO This is a risky call, is there any marvel hero with no thumb?
    // Really makes me think
    val thumbnail: MarvelThumbnailDTO
)

fun MarvelCharacterDTO.asCharacter(): MarvelCharacter {
    return MarvelCharacter(id, name, description, "${thumbnail.path}.${thumbnail.extension}")
}