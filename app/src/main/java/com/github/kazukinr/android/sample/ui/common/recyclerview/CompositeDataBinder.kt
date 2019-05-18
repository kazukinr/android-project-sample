package com.github.kazukinr.android.sample.ui.common.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

/**
 * Marker interface to represent the data binder cannot be composed by [CompositeDataBinder].
 */
interface UnComposable

class CompositeDataBinder(
    protected val binders: MutableList<DataBinder>
) : DataBinder() {

    constructor(vararg binders: DataBinder) : this(binders.toMutableList())

    init {
        binders.forEachIndexed { index, binder ->
            if (binder is UnComposable) {
                throw IllegalArgumentException("UnComposable binder founds.")
            }
            createListUpdateCallback(index).let(binder::setListUpdateCallback)
        }
    }

    override val itemCount: Int get() = binders.sumBy { it.itemCount }

    override fun getItemViewType(position: Int): Int {
        runAsChild(position) { child, childPosition ->
            return child.getItemViewType(childPosition)
        }
        throw IllegalArgumentException("Invalid position: $position")
    }

    override fun canCreateViewHolder(viewType: Int): Boolean =
        binders.any { it.canCreateViewHolder(viewType) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binders.forEach { binder ->
            if (binder.canCreateViewHolder(viewType)) {
                return binder.onCreateViewHolder(parent, viewType)
            }
        }
        throw IllegalArgumentException("Invalid viewType: $viewType")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        getBinderPosition: RecyclerView.ViewHolder.() -> Int?
    ) {
        runAsChild(position) { child, childPosition ->
            child.onBindViewHolder(holder, childPosition) {
                val parentPosition = getBinderPosition()
                convertToChildPosition(parentPosition)
            }
        }
    }

    override fun onAttached() {
        super.onAttached()
        binders.forEach { it.onAttached() }
    }

    override fun onDetached() {
        super.onDetached()
        binders.forEach { it.onDetached() }
    }

    override fun onBinderPropertyChanged() {
        // Nothing to do
    }

    fun getBinderByPosition(position: Int): DataBinder {
        var itemCount = 0
        binders.forEach {
            if (position < itemCount + it.itemCount) {
                return it
            }
            itemCount += it.itemCount
        }
        throw IllegalArgumentException("Invalid position: ${position}")
    }

    fun getStartPosition(binder: DataBinder): Int {
        var itemCount = 0
        binders.forEach {
            if (it == binder) {
                return itemCount
            }
            itemCount += it.itemCount
        }
        throw IllegalArgumentException("Invalid binder: ${binder.javaClass.simpleName}")
    }

    fun addBinder(binder: DataBinder) {
        binders.add(binder)
        createListUpdateCallback(binders.lastIndex).let(binder::setListUpdateCallback)
    }

    fun clearBinders() {
        if (binders.isEmpty()) return

        val count = itemCount
        binders.forEach { it.setListUpdateCallback(null) }
        binders.clear()
        notifyItemRangeRemoved(0, count)
    }

    private fun createListUpdateCallback(childIndex: Int): ListUpdateCallback =
        object : ListUpdateCallback {
            override fun onChanged(position: Int, count: Int, payload: Any?) {
                val parentPosition = convertChildPositionToParentPosition(childIndex, position)
                notifyItemRangeChanged(parentPosition, count, payload)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                val parentFromPosition = convertChildPositionToParentPosition(childIndex, fromPosition)
                val parentToPosition = convertChildPositionToParentPosition(childIndex, toPosition)
                notifyItemMoved(parentFromPosition, parentToPosition)
            }

            override fun onInserted(position: Int, count: Int) {
                val parentPosition = convertChildPositionToParentPosition(childIndex, position)
                notifyItemRangeInserted(parentPosition, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                val parentPosition = convertChildPositionToParentPosition(childIndex, position)
                notifyItemRangeRemoved(parentPosition, count)
            }

            private fun convertChildPositionToParentPosition(childIndex: Int, childPosition: Int): Int =
                binders.take(childIndex).sumBy { it.itemCount } + childPosition
        }

    private inline fun runAsChild(position: Int, block: (DataBinder, Int) -> Unit) {
        var itemCount = 0
        binders.forEach { child ->
            if (position < itemCount + child.itemCount) {
                val childPosition = position - itemCount
                return block(child, childPosition)
            }
            itemCount += child.itemCount
        }
        throw IllegalArgumentException("Invalid position: $position")
    }

    private fun convertToChildPosition(parentPosition: Int?): Int? {
        if (parentPosition == null || parentPosition < 0) return null

        var itemCount = 0
        binders.forEach { child ->
            if (parentPosition < itemCount + child.itemCount) {
                return parentPosition - itemCount
            }
            itemCount += child.itemCount
        }

        return null
    }
}
