package com.github.kazukinr.android.sample.di.module

import com.github.kazukinr.android.sample.di.scope.ActivityScope
import com.github.kazukinr.android.sample.ui.main.MainActivity
import com.github.kazukinr.android.sample.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    fun contributesMainActivity(): MainActivity
}
