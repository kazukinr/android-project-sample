package com.github.kazukinr.android.sample.ui.binding_sample

import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.di.mapkey.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BindingSampleFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(BindingSampleViewModel::class)
    fun bindsBindingSampleViewModel(viewModel: BindingSampleViewModel): ViewModel
}