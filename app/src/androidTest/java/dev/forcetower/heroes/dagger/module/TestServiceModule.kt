package dev.forcetower.heroes.dagger.module

import dagger.Module
import dagger.Provides
import dev.forcetower.heroes.core.source.remote.FakeMarvelService
import dev.forcetower.heroes.core.source.remote.MarvelService
import javax.inject.Singleton

@Module
object TestServiceModule {
    @Provides
    @Singleton
    fun provideFakeService(): MarvelService {
        return FakeMarvelService()
    }
}