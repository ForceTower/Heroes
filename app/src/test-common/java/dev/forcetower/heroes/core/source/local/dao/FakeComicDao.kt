package dev.forcetower.heroes.core.source.local.dao

import dev.forcetower.heroes.core.model.MarvelFakeDataFactory
import dev.forcetower.heroes.core.model.persistence.MarvelComic
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeComicDao : ComicDao() {
    override fun mostExpensive(characterId: Int): Flow<MarvelExpensiveComic?> {
        return flowOf(MarvelFakeDataFactory.makeMarvelExpensiveComic())
    }

    override suspend fun insert(value: MarvelComic) {}
    override suspend fun insertIgnore(value: MarvelComic) {}
    override suspend fun insertAll(values: List<MarvelComic>) {}
    override suspend fun insertAllIgnore(values: List<MarvelComic>) {}
    override suspend fun update(value: MarvelComic) {}
}