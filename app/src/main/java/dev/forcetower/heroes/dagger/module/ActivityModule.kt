package dev.forcetower.heroes.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.forcetower.heroes.view.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun main(): MainActivity
}