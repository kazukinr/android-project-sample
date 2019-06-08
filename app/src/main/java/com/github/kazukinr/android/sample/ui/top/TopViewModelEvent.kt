package com.github.kazukinr.android.sample.ui.top

sealed class TopViewModelEvent {

    object NavigateToBindingSample : TopViewModelEvent()

    object NavigateToRoomRxSample : TopViewModelEvent()

    object NavigateToApiCallSample : TopViewModelEvent()

    object NavigateToExoPlayerSample : TopViewModelEvent()
}