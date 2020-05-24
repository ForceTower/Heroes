package dev.forcetower.heroes.core.model.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(primaryKeys = ["comicId", "type", "price"], foreignKeys = [
    ForeignKey(entity = MarvelComic::class, parentColumns = ["id"], childColumns = ["comicId"], onUpdate = CASCADE, onDelete = CASCADE)
])
data class MarvelComicPrice(
    val comicId: Int,
    val type: String,
    val price: Float
)