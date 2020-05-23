package dev.forcetower.heroes.core.source.remote

import dev.forcetower.heroes.core.model.MarvelFakeDataFactory
import dev.forcetower.heroes.core.model.dto.MarvelCharacterDTO
import dev.forcetower.heroes.core.model.dto.MarvelComicDTO
import dev.forcetower.heroes.core.model.dto.response.GeneralResponse

class FakeMarvelService : MarvelService {
    override suspend fun characters(offset: Int, limit: Int): GeneralResponse<MarvelCharacterDTO> {
        return MarvelFakeDataFactory.makeCharactersResponse()
    }

    override suspend fun comics(
        characterId: Int,
        offset: Int,
        limit: Int
    ): GeneralResponse<MarvelComicDTO> {
        return MarvelFakeDataFactory.makeComicsResponse()
    }
}