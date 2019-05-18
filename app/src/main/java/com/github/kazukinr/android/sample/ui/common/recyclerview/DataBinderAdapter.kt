package com.github.kazukinr.android.sample.ui.common.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

class DataBinderAdapter(
    private val binder: DataBinder
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var updateCallback: ListUpdateCallback? = null

    init {
        binder.setListUpdateCallback(object : ListUpdateCallback {
            override fun onChanged(position: Int, count: Int, payload: Any?) {
                updateCallback?.onChanged(position, count, payload)
                notifyItemRangeChanged(position, count, payload)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                updateCallback?.onMoved(fromPosition, toPosition)
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onInserted(position: Int, count: Int) {
                updateCallback?.onInserted(position, count)
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                updateCallback?.onRemoved(position, count)
                notifyItemRangeRemoved(position, count)
            }
        })
    }

    fun setListUpdateCallback(listUpdateCallback: ListUpdateCallback?) {
        updateCallback = listUpdateCallback
    }

    override fun getItemCount(): Int = binder.itemCount

    override fun getItemViewType(position: Int): Int = binder.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        binder.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        binder.onBindViewHolder(holder, position) { adapterPosition.takeIf { it >= 0 } }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        binder.onAttached()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        binder.onDetached()
    }
}
