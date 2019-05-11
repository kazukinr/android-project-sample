package com.github.kazukinr.android.sample.di.module

import androidx.lifecycle.ViewModelProvider
import com.github.kazukinr.android.sample.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
