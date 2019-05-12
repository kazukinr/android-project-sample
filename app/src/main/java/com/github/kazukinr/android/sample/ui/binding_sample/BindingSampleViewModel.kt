package com.github.kazukinr.android.sample.ui.binding_sample

import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.event.EventLiveData
import javax.inject.Inject

class BindingSampleViewModel @Inject constructor() : ViewModel(), BindingSampleFragment.Listener {

    val viewData = BindingSampleViewData()

    val event = EventLiveData<BindingSampleViewModelEvent>()

    override fun onBackToTopClicked() {
        event.emitEvent(BindingSampleViewModelEvent.NavigateToTop)
    }
}