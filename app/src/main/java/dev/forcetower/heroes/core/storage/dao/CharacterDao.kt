package dev.forcetower.heroes.core.storage.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharacterDao : BaseDao<MarvelCharacter>() {
    @Query("SELECT * FROM MarvelCharacter WHERE id = :id")
    abstract fun character(id: Int): Flow<MarvelCharacter>
}