package com.github.kazukinr.android.sample.ui.api_call_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.ApiCallSampleFragmentBinding
import com.github.kazukinr.android.sample.ui.DisposableLifecycleObserver
import com.github.kazukinr.android.sample.ui.get
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ApiCallSampleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelFactory.get<ApiCallSampleViewModel>(this)
    }

    private var binding: ApiCallSampleFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(DisposableLifecycleObserver(viewModel))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<ApiCallSampleFragmentBinding>(
            inflater,
            R.layout.api_call_sample_fragment,
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