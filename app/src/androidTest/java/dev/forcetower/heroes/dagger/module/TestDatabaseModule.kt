package dev.forcetower.heroes.dagger.module

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dev.forcetower.heroes.core.source.local.MarvelDatabase
import javax.inject.Singleton

@Module
object TestDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(): MarvelDatabase {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java)
            .build()
    }
}
