package dev.forcetower.heroes.core.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.persistence.MarvelComic
import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice
import dev.forcetower.heroes.core.storage.dao.CharacterDao
import dev.forcetower.heroes.core.storage.dao.ComicDao
import dev.forcetower.heroes.core.storage.dao.PriceDao

@Database(entities = [
    MarvelCharacter::class,
    MarvelComic::class,
    MarvelComicPrice::class
], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characters(): CharacterDao
    abstract fun comics(): ComicDao
    abstract fun prices(): PriceDao
}