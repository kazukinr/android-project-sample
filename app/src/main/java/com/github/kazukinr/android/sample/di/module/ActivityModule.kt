package com.github.kazukinr.android.sample.di.module

import com.github.kazukinr.android.sample.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun contributesMainActivity(): MainActivity
}