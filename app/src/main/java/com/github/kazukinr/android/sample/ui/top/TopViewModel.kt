package com.github.kazukinr.android.sample.ui.top

import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.event.EventLiveData
import javax.inject.Inject

class TopViewModel @Inject constructor() : ViewModel(), TopFragment.Listener {

    val event = EventLiveData<TopViewModelEvent>()

    override fun onBindingSampleClicked() {
        event.emitEvent(TopViewModelEvent.NavigateToBindingSample)
    }

    override fun onRoomRxSampleClicked() {
        event.emitEvent(TopViewModelEvent.NavigateToRoomRxSample)
    }
}
