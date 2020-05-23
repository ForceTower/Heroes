package dev.forcetower.heroes.core.source.remote

import dev.forcetower.heroes.core.model.MarvelFakeDataFactory
import dev.forcetower.heroes.core.model.dto.MarvelCharacterDTO
import dev.forcetower.heroes.core.model.dto.MarvelComicDTO
import dev.forcetower.heroes.core.model.dto.response.GeneralResponse

class FakeMarvelService : MarvelService {
    var failsWith: String? = null

    override suspend fun characters(offset: Int, limit: Int): GeneralResponse<MarvelCharacterDTO> {
        if (failsWith != null) throw IllegalStateException(failsWith)
        return MarvelFakeDataFactory.makeCharactersResponse()
    }

    override suspend fun comics(
        characterId: Int,
        offset: Int,
        limit: Int
    ): GeneralResponse<MarvelComicDTO> {
        if (failsWith != null) throw IllegalStateException(failsWith)
        return MarvelFakeDataFactory.makeComicsResponse()
    }
}