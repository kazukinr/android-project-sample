package com.github.kazukinr.android.sample.ui.user

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.kazukinr.android.data.user.entity.User
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.ui.common.recyclerview.ViewDataBinder

class UserLineViewDataBinder : ViewDataBinder<UserLineView>() {

    var users: List<User>? by binderProperty(null)
    var params: List<Param>? by listItemProperty(null, Param.DIFF_CALLBACK)

    override val itemCount: Int
        get() = params?.size ?: 0

    override val viewType: Int = R.layout.user_line_view

    override fun createView(context: Context): UserLineView =
        UserLineView(context)

    override fun onBindView(
        view: UserLineView,
        holder: RecyclerView.ViewHolder,
        position: Int,
        getBinderPosition: RecyclerView.ViewHolder.() -> Int?
    ) {
        val param = params?.getOrNull(position) ?: return
        view.setParam(param)
    }

    override fun onBinderPropertyChanged() {
        params = users?.map {
            Param(
                userId = it.id,
                userName = it.name.orEmpty()
            )
        }
    }

    data class Param(
        override val userId: Long,
        override val userName: String
    ) : UserLineView.Param {

        companion object {
            val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Param>() {
                override fun areItemsTheSame(oldItem: Param, newItem: Param): Boolean =
                    oldItem.userId == newItem.userId

                override fun areContentsTheSame(oldItem: Param, newItem: Param): Boolean =
                    oldItem == newItem
            }
        }
    }
}