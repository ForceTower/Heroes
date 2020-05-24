package dev.forcetower.heroes

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.forcetower.heroes.core.source.remote.MarvelService
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var component: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var service: MarvelService

    override fun androidInjector() = component
}