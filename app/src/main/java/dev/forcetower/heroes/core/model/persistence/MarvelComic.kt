package dev.forcetower.heroes.core.model.persistence

data class MarvelComic(
    val id: Int,
    val digitalId: Int,
    val issueNumber: Int,
    val title: String,
    val description: String,
    val prices: List<MarvelComicPrice>,
    val thumbnail: String
)