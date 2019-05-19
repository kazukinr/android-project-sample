package com.github.kazukinr.android.sample.ui.api_call_sample

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ApiCallSampleViewHeaderData : BaseObservable() {

    @get: Bindable
    var inputUserId: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.inputUserId)
                notifyPropertyChanged(BR.searchButtonEnabled)
            }
        }

    @get: Bindable
    var isSearchButtonEnabled: Boolean = false
        get() = inputUserId?.isNotEmpty() ?: false
        private set
}
