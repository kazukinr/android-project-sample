package com.github.kazukinr.android.sample.ui.room_rx_sample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import com.github.kazukinr.android.data.user.entity.User
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.RoomRxSampleViewBinding
import com.github.kazukinr.android.sample.ui.common.recyclerview.DataBinderAdapter
import com.github.kazukinr.android.sample.ui.user.UserLineViewDataBinder
import kotlinx.android.synthetic.main.room_rx_sample_view.view.*

class RoomRxSampleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val userLineBinder = UserLineViewDataBinder()
    private val adapter = DataBinderAdapter(userLineBinder)

    private val binding = DataBindingUtil.inflate<RoomRxSampleViewBinding>(
        LayoutInflater.from(context),
        R.layout.room_rx_sample_view,
        this,
        true
    ).apply {
        (userList.itemAnimator as? DefaultItemAnimator)?.also {
            it.supportsChangeAnimations = false
        }
        userList.adapter = adapter
    }

    fun setHeaderData(data: RoomRxSampleViewHeaderData?) {
        binding.headerData = data
    }

    fun setUsers(users: List<User>?) {
        userLineBinder.users = users
    }

    fun setListener(listener: Listener?) {
        binding.listener = listener
    }

    interface Listener {

        fun onAddUserClicked()
    }
}
