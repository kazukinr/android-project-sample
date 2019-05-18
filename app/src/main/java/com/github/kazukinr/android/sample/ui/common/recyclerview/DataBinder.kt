package com.github.kazukinr.android.sample.ui.common.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Completable
import io.reactivex.FlowableSubscriber
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class DataBinder {

    private var listUpdateCallback: ListUpdateCallback? = null
    private val binderPropertyChangedProcessor = PublishProcessor.create<Unit>().toSerialized()

    abstract val itemCount: Int

    init {
        binderPropertyChangedProcessor.onBackpressureLatest()
            .subscribe(object : FlowableSubscriber<Unit> {
                private var subscription: Subscription? = null

                override fun onSubscribe(s: Subscription) {
                    this.subscription = s
                    s.request(1)
                }

                override fun onNext(t: Unit) {
                    onBinderPropertyChanged()
                    subscription?.request(1)
                }

                override fun onComplete() {
                    // nothing to do
                }

                override fun onError(t: Throwable) {
                    Timber.e(t)
                }
            })
    }

    /**
     * @return the view type that must be unique in all [DataBinder]s
     */
    abstract fun getItemViewType(position: Int): Int

    abstract fun canCreateViewHolder(viewType: Int): Boolean

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        getBinderPosition: RecyclerView.ViewHolder.() -> Int?
    )

    abstract fun onBinderPropertyChanged()

    open fun onAttached() {
        // nothing to do
    }

    open fun onDetached() {
        // nothing to do
    }

    internal fun setListUpdateCallback(listUpdateCallback: ListUpdateCallback?) {
        this.listUpdateCallback = listUpdateCallback
    }

    protected fun notifyItemRangeInserted(position: Int, count: Int) {
        Timber.v("notifyItemRangeInserted: position = $position, count = $count")
        listUpdateCallback?.onInserted(position, count)
    }

    protected fun notifyItemRangeRemoved(position: Int, count: Int) {
        Timber.v("notifyItemRangeRemoved: position = $position, count = $count")
        listUpdateCallback?.onRemoved(position, count)
    }

    protected fun notifyItemMoved(fromPosition: Int, toPosition: Int) {
        Timber.v("notifyItemMoved: fromPosition = $fromPosition, toPosition = $toPosition")
        listUpdateCallback?.onMoved(fromPosition, toPosition)
    }

    protected fun notifyItemRangeChanged(position: Int, count: Int, payload: Any? = null) {
        Timber.v("notifyItemRangeChanged: position = $position, count = $count")
        listUpdateCallback?.onChanged(position, count, payload)
    }

    protected fun notifyAllItemsChanged(payload: Any? = null) {
        if (itemCount > 0) {
            Timber.v("notifyAllItemsChanged")
            notifyItemRangeChanged(0, itemCount, payload)
        }
    }

    protected fun notifyDiffResult(result: DiffUtil.DiffResult) {
        listUpdateCallback?.let(result::dispatchUpdatesTo)
    }

    private fun notifyBinderPropertyChanged() {
        Completable.fromAction { binderPropertyChangedProcessor.onNext(Unit) }
            .subscribe({}, Timber::e)
    }

    protected fun <T> listItemProperty(
        initialValue: List<T>?,
        itemDiffCallback: DiffUtil.ItemCallback<T>,
        detectMove: Boolean = false
    ): ReadWriteProperty<DataBinder, List<T>?> =
        object : ReadWriteProperty<DataBinder, List<T>?> {

            private var value: List<T>? = initialValue
            private var maxScheduledGeneration: Long = 0

            override fun getValue(
                thisRef: DataBinder,
                property: KProperty<*>
            ): List<T>? = value

            override fun setValue(
                thisRef: DataBinder,
                property: KProperty<*>,
                value: List<T>?
            ) {
                val oldList = this.value
                val newList = value

                if (newList === oldList) {
                    // nothing to do
                    return
                }

                val runGeneration = ++maxScheduledGeneration

                // remove all
                if (newList == null) {
                    val countRemoved = oldList!!.size
                    this.value = null
                    thisRef.notifyItemRangeRemoved(0, countRemoved)
                    return
                }

                // insert all
                if (oldList == null) {
                    this.value = newList
                    thisRef.notifyItemRangeInserted(0, newList.size)
                    return
                }

                Single.fromCallable {
                    DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                        override fun getOldListSize(): Int = oldList.size

                        override fun getNewListSize(): Int = newList.size

                        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                            itemDiffCallback.areItemsTheSame(
                                oldList[oldItemPosition],
                                newList[newItemPosition]
                            )

                        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                            itemDiffCallback.areContentsTheSame(
                                oldList[oldItemPosition],
                                newList[newItemPosition]
                            )
                    }, detectMove)
                }
                    .also {
                        if (!detectMove) {
                            it.subscribeOn(Schedulers.computation())
                            it.observeOn(AndroidSchedulers.mainThread())
                        }
                    }
                    .subscribe({ result ->
                        if (maxScheduledGeneration == runGeneration) {
                            this.value = newList
                            thisRef.notifyDiffResult(result)
                        }
                    }, Timber::e)
            }
        }

    protected fun <T> binderProperty(
        initialValue: T
    ): ReadWriteProperty<DataBinder, T> =
        object : ReadWriteProperty<DataBinder, T> {

            private var value: T = initialValue

            override fun getValue(
                thisRef: DataBinder,
                property: KProperty<*>
            ): T = value

            override fun setValue(
                thisRef: DataBinder,
                property: KProperty<*>,
                value: T
            ) {
                if (this.value !== value) {
                    this.value = value
                    thisRef.notifyBinderPropertyChanged()
                }
            }
        }

    internal class ViewHolder<out V : View>(
        val view: V,
        val viewType: Int
    ) : RecyclerView.ViewHolder(view)
}
