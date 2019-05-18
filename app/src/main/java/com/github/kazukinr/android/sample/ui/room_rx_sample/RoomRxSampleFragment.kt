package com.github.kazukinr.android.sample.ui.room_rx_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.RoomRxSampleFragmentBinding
import com.github.kazukinr.android.sample.ui.DisposableLifecycleObserver
import com.github.kazukinr.android.sample.ui.get
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RoomRxSampleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelFactory.get<RoomRxSampleViewModel>(this)
    }

    private var binding: RoomRxSampleFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(DisposableLifecycleObserver(viewModel))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<RoomRxSampleFragmentBinding>(
            inflater,
            R.layout.room_rx_sample_fragment,
            container,
            false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel = viewModel
    }
}