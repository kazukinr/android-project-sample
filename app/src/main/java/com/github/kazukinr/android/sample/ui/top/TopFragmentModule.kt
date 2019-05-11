package com.github.kazukinr.android.sample.ui.top

import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.di.mapkey.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TopFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    fun bindsTopViewModel(viewModel: TopViewModel): ViewModel
}
