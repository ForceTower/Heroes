package dev.forcetower.heroes.dagger.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.forcetower.heroes.core.base.BaseViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}