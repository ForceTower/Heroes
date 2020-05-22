package dev.forcetower.heroes.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.forcetower.heroes.HeroApp
import dev.forcetower.heroes.dagger.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    ServiceModule::class,
    DatabaseModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<HeroApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: HeroApp): Builder
        fun build(): AppComponent
    }
}