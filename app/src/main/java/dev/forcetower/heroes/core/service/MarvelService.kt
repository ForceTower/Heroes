package dev.forcetower.heroes.core.service

import retrofit2.http.GET

interface MarvelService {
    @GET("public/characters")
    suspend fun characters()
}