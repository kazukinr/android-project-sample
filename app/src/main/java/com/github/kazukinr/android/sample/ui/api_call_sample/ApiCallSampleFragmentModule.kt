package com.github.kazukinr.android.sample.ui.api_call_sample

import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.di.mapkey.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ApiCallSampleFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ApiCallSampleViewModel::class)
    fun bindsApiCallSampleViewModel(viewModel: ApiCallSampleViewModel): ViewModel
}