package dev.forcetower.heroes.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.forcetower.heroes.view.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun main(): MainActivity
}