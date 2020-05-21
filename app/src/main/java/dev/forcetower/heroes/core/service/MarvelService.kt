package dev.forcetower.heroes.core.service

import dev.forcetower.heroes.core.model.dto.response.GeneralResponse
import dev.forcetower.heroes.core.model.dto.MarvelCharacterDTO
import dev.forcetower.heroes.core.model.dto.MarvelComicDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun characters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): GeneralResponse<MarvelCharacterDTO>

    @GET("characters/{characterId}/comics")
    suspend fun comics(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): GeneralResponse<MarvelComicDTO>
}