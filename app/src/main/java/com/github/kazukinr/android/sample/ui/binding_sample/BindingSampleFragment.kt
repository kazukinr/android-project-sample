package com.github.kazukinr.android.sample.ui.binding_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.BindingSampleFragmentBinding
import com.github.kazukinr.android.sample.event.EventLiveDataObserver
import com.github.kazukinr.android.sample.ui.get
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BindingSampleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelFactory.get<BindingSampleViewModel>(this)
    }

    private var binding: BindingSampleFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<BindingSampleFragmentBinding>(
            inflater,
            R.layout.binding_sample_fragment,
            container,
            false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewData = viewModel.viewData
        binding?.listener = viewModel
        viewModel.event.observe(this, EventLiveDataObserver(this::handleViewModelEvent))
    }

    private fun handleViewModelEvent(event: BindingSampleViewModelEvent) {
        when (event) {
            is BindingSampleViewModelEvent.NavigateToTop -> {
                binding?.buttonBackToTop?.also {
                    Navigation.findNavController(it).popBackStack(R.id.top_fragment, false)
                }
            }
        }
    }

    interface Listener {

        fun onBackToTopClicked()
    }
}