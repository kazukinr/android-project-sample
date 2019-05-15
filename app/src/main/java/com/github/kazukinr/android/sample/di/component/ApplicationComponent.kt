package com.github.kazukinr.android.sample.di.component

import com.github.kazukinr.android.sample.App
import com.github.kazukinr.android.sample.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}