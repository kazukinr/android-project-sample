package com.github.kazukinr.android.sample.ui.github

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.RepoLineViewBinding

class RepoLineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = DataBindingUtil.inflate<RepoLineViewBinding>(
        LayoutInflater.from(context),
        R.layout.repo_line_view,
        this,
        true
    ).apply {
        viewData = ViewData()
    }

    fun setParam(param: Param) {
        binding.viewData?.update(param)
        binding.executePendingBindings()
    }

    interface Param {

        val name: String
        val description: String
    }

    class ViewData {

        val name = ObservableField<String>()
        val description = ObservableField<String>()

        fun update(param: Param) {
            name.set(param.name)
            description.set(param.description)
        }
    }
}