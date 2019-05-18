package com.github.kazukinr.android.sample.ui.room_rx_sample

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class RoomRxSampleViewHeaderData : BaseObservable() {

    @get: Bindable
    var inputUserName: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.inputUserName)
                notifyPropertyChanged(BR.addButtonEnabled)
            }
        }

    @get: Bindable
    var isAddButtonEnabled: Boolean = false
        get() = inputUserName?.isNotEmpty() ?: false
        private set
}
