package dev.forcetower.heroes.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.forcetower.heroes.TestApplication
import dev.forcetower.heroes.dagger.module.ActivityModule
import dev.forcetower.heroes.dagger.module.AppModule
import dev.forcetower.heroes.dagger.module.TestDatabaseModule
import dev.forcetower.heroes.dagger.module.TestServiceModule
import dev.forcetower.heroes.dagger.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    TestDatabaseModule::class,
    TestServiceModule::class,
    ViewModelModule::class
])
interface TestComponent : AndroidInjector<TestApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): TestComponent
    }
}