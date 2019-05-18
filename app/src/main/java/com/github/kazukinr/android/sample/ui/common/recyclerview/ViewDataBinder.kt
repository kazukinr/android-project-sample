package com.github.kazukinr.android.sample.ui.common.recyclerview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ViewDataBinder<V : View> : DataBinder() {

    abstract val viewType: Int

    abstract fun createView(context: Context): V

    abstract fun onBindView(
        view: V,
        holder: RecyclerView.ViewHolder,
        position: Int,
        getBinderPosition: RecyclerView.ViewHolder.() -> Int?
    )

    protected open fun applyLayoutParams(view: View) {
        view.layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.MarginLayoutParams.MATCH_PARENT,
            ViewGroup.MarginLayoutParams.WRAP_CONTENT
        )
    }

    final override fun getItemViewType(position: Int): Int = this.viewType

    final override fun canCreateViewHolder(viewType: Int): Boolean =
        viewType == this.viewType

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            createView(parent.context).apply(this::applyLayoutParams),
            this.viewType
        )

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        getBinderPosition: RecyclerView.ViewHolder.() -> Int?
    ) {
        onBindView((holder as ViewHolder<V>).view, holder, position, getBinderPosition)
    }
}
