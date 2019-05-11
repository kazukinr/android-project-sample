package com.github.kazukinr.android.sample.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.TopFragmentBinding
import com.github.kazukinr.android.sample.event.EventLiveDataObserver
import com.github.kazukinr.android.sample.ui.get
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class TopFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelFactory.get<TopViewModel>(this)
    }

    private var binding: TopFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<TopFragmentBinding>(
            inflater,
            R.layout.top_fragment,
            container,
            false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.listener = viewModel

        viewModel.event.observe(this, EventLiveDataObserver(this::handleViewModelEvent))
    }

    private fun handleViewModelEvent(event: TopViewModelEvent) {
        Timber.i("handleViewModelEvent : $event")
    }

    interface Listener {

        fun onBindingSampleClicked()
    }
}
