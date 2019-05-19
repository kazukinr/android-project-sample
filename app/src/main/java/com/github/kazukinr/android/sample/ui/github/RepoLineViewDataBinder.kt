package com.github.kazukinr.android.sample.ui.github

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.kazukinr.android.data.github.dto.Repo
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.ui.common.recyclerview.ViewDataBinder

class RepoLineViewDataBinder : ViewDataBinder<RepoLineView>() {

    var repos: List<Repo>? by binderProperty(null)
    var params: List<Param>? by listItemProperty(null, Param.DIFF_CALLBACK)

    override val itemCount: Int
        get() = params?.size ?: 0

    override val viewType: Int = R.layout.repo_line_view

    override fun createView(context: Context): RepoLineView =
        RepoLineView(context)

    override fun onBindView(
        view: RepoLineView,
        holder: RecyclerView.ViewHolder,
        position: Int,
        getBinderPosition: RecyclerView.ViewHolder.() -> Int?
    ) {
        val param = params?.getOrNull(position) ?: return
        view.setParam(param)
    }

    override fun onBinderPropertyChanged() {
        params = repos?.map {
            Param(
                name = it.name,
                description = it.description.orEmpty()
            )
        }
    }

    data class Param(
        override val name: String,
        override val description: String
    ) : RepoLineView.Param {

        companion object {
            val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Param>() {
                override fun areItemsTheSame(oldItem: Param, newItem: Param): Boolean =
                    oldItem.name == newItem.name

                override fun areContentsTheSame(oldItem: Param, newItem: Param): Boolean =
                    oldItem == newItem
            }
        }
    }
}