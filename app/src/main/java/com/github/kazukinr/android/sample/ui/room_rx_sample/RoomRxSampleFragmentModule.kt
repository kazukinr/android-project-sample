package com.github.kazukinr.android.sample.ui.room_rx_sample

import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.di.mapkey.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RoomRxSampleFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(RoomRxSampleViewModel::class)
    fun bindsRoomRxSampleViewModel(viewModel: RoomRxSampleViewModel): ViewModel
}