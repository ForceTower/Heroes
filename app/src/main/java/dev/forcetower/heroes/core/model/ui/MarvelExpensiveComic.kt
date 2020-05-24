package dev.forcetower.heroes.core.model.ui

data class MarvelExpensiveComic(
    val issueNumber: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val type: String,
    val price: Float
)