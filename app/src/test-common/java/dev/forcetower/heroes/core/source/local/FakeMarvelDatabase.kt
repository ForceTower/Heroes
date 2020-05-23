package dev.forcetower.heroes.core.source.local

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import dev.forcetower.heroes.core.source.local.dao.CharacterDao
import dev.forcetower.heroes.core.source.local.dao.ComicDao
import dev.forcetower.heroes.core.source.local.dao.FakeCharacterDao
import dev.forcetower.heroes.core.source.local.dao.FakeComicDao
import dev.forcetower.heroes.core.source.local.dao.PriceDao
import org.mockito.Mockito

class FakeMarvelDatabase : MarvelDatabase() {
    override fun characters(): CharacterDao {
        return FakeCharacterDao()
    }

    override fun comics(): ComicDao {
        return FakeComicDao()
    }

    override fun prices(): PriceDao {
        return Mockito.mock(PriceDao::class.java)
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        return Mockito.mock(SupportSQLiteOpenHelper::class.java)
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return Mockito.mock(InvalidationTracker::class.java)
    }

    override fun clearAllTables() {}
}