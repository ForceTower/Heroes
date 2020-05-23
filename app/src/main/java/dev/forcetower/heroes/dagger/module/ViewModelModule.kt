package dev.forcetower.heroes.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.forcetower.heroes.core.base.BaseViewModelFactory
import dev.forcetower.heroes.dagger.annotation.ViewModelKey
import dev.forcetower.heroes.view.characters.CharactersViewModel
import dev.forcetower.heroes.view.details.DetailsViewModel
import dev.forcetower.heroes.view.expensive.ExpensiveViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(vm: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(vm: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensiveViewModel::class)
    abstract fun bindExpensiveViewModel(vm: ExpensiveViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}