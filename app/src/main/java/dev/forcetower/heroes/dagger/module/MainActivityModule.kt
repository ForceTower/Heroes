package dev.forcetower.heroes.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.forcetower.heroes.view.characters.CharactersFragment
import dev.forcetower.heroes.view.details.DetailsFragment
import dev.forcetower.heroes.view.expensive.ExpensiveFragment

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun characters(): CharactersFragment
    @ContributesAndroidInjector
    abstract fun details(): DetailsFragment
    @ContributesAndroidInjector
    abstract fun expensive(): ExpensiveFragment
}