package dev.forcetower.heroes.core.model.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = MarvelCharacter::class, parentColumns = ["id"], childColumns = ["characterId"], onDelete = CASCADE, onUpdate = CASCADE)
], indices = [
    Index(value = ["characterId"])
])
data class MarvelComic(
    @PrimaryKey
    val id: Int,
    val digitalId: Int,
    val issueNumber: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val characterId: Int
)