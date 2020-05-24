package dev.forcetower.heroes.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.persistence.MarvelComic
import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice
import dev.forcetower.heroes.core.source.local.dao.CharacterDao
import dev.forcetower.heroes.core.source.local.dao.ComicDao
import dev.forcetower.heroes.core.source.local.dao.PriceDao

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