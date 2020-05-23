package dev.forcetower.heroes.dagger.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dev.forcetower.heroes.Constants
import dev.forcetower.heroes.core.source.remote.MarvelService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ServiceModule {
    @Provides
    @Singleton
    fun provideTMDbService(client: OkHttpClient, gson: Gson): MarvelService {
        return Retrofit.Builder()
            .baseUrl("http://${Constants.MARVEL_SERVICE}/v1/public/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MarvelService::class.java)
    }
}