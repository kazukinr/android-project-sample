package com.github.kazukinr.android.sample.ui.api_call_sample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import com.github.kazukinr.android.data.github.dto.Repo
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.ApiCallSampleViewBinding
import com.github.kazukinr.android.sample.ui.common.recyclerview.DataBinderAdapter
import com.github.kazukinr.android.sample.ui.github.RepoLineViewDataBinder
import com.github.kazukinr.android.sample.ui.user.UserLineViewDataBinder

class ApiCallSampleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val repoLineBinder = RepoLineViewDataBinder()
    private val adapter = DataBinderAdapter(repoLineBinder)

    private val binding = DataBindingUtil.inflate<ApiCallSampleViewBinding>(
        LayoutInflater.from(context),
        R.layout.api_call_sample_view,
        this,
        true
    ).apply {
        (repoList.itemAnimator as? DefaultItemAnimator)?.also {
            it.supportsChangeAnimations = false
        }
        repoList.adapter = adapter
    }

    fun setHeaderData(data: ApiCallSampleViewHeaderData?) {
        binding.headerData = data
    }

    fun setRepos(repos: List<Repo>?) {
        repoLineBinder.repos = repos
    }

    fun setListener(listener: Listener?) {
        binding.listener = listener
    }

    interface Listener {

        fun onSearchUserReposClicked()
    }
}
