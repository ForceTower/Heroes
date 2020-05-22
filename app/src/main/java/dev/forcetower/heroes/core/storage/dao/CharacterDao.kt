package dev.forcetower.heroes.core.storage.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.ui.MarvelCharacterDetailed
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharacterDao : BaseDao<MarvelCharacter>() {
    @Transaction
    @Query("SELECT * FROM MarvelCharacter WHERE id = :id")
    abstract fun character(id: Int): Flow<MarvelCharacterDetailed>
}