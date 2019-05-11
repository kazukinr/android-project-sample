package com.github.kazukinr.android.sample.ui.binding_sample

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.github.kazukinr.android.sample.BR

class BindingSampleViewData : BaseObservable() {

    @get: Bindable
    var inputText: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.inputText)
                notifyPropertyChanged(BR.inputTextLength)
            }
        }

    @get: Bindable
    var inputTextLength: Int = 0
        get() = inputText?.length ?: 0
        private set
}
