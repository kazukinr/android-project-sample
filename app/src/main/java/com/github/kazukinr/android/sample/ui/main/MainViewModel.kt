package com.github.kazukinr.android.sample.ui.main

import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    fun ping() {
        Timber.i("MainViewModel has been successfully injected.")
    }
}