package dev.forcetower.heroes

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var component: DispatchingAndroidInjector<Any>

    override fun androidInjector() = component
}