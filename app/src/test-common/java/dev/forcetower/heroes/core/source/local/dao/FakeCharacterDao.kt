package dev.forcetower.heroes.core.source.local.dao

import dev.forcetower.heroes.core.model.MarvelFakeDataFactory
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterDao : CharacterDao() {
    override fun character(id: Int): Flow<MarvelCharacter> {
        return flowOf(MarvelFakeDataFactory.makeRealCharacter(id))
    }
    override suspend fun insert(value: MarvelCharacter) {}
    override suspend fun insertIgnore(value: MarvelCharacter) {}
    override suspend fun insertAll(values: List<MarvelCharacter>) {}
    override suspend fun insertAllIgnore(values: List<MarvelCharacter>) {}
    override suspend fun update(value: MarvelCharacter) {}
}