package com.github.kazukinr.android.sample.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.ui.get
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelFactory.get<MainViewModel>(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel.ping()
    }
}
