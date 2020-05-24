package dev.forcetower.heroes.core.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarvelCharacter(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
)