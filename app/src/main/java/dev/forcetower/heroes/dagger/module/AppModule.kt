package dev.forcetower.heroes.dagger.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dev.forcetower.heroes.BuildConfig
import dev.forcetower.heroes.Constants
import dev.forcetower.heroes.HeroApp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: HeroApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideInterceptor() = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val host = request.url.host
            return if (host.contains(Constants.MARVEL_SERVICE, ignoreCase = true)) {
                val headers = request.headers.newBuilder()
                    .add("Accept", "application/json")
                    .build()

                val url = request.url.newBuilder()
                    .addQueryParameter("ts", Constants.MARVEL_TS)
                    .addQueryParameter("apikey", Constants.MARVEL_PUBLIC_KEY)
                    .addQueryParameter("hash", Constants.MARVEL_HASH_KEY)
                    .build()

                val renewed = request.newBuilder().url(url).headers(headers).build()

                chain.proceed(renewed)
            } else {
                val nRequest = request.newBuilder().addHeader("Accept", "application/json").build()
                chain.proceed(nRequest)
            }
        }
    }

    @Provides
    @Singleton
    fun provideClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BASIC
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }
}
