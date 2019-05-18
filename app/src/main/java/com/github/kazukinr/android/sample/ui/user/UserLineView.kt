package com.github.kazukinr.android.sample.ui.user

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.UserLineViewBinding

class UserLineView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = DataBindingUtil.inflate<UserLineViewBinding>(
            LayoutInflater.from(context),
            R.layout.user_line_view,
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

        val userId: Long
        val userName: String
    }

    class ViewData {

        val userId = ObservableField<String>()
        val userName = ObservableField<String>()

        fun update(param: Param) {
            userId.set(param.userId.toString())
            userName.set(param.userName)
        }
    }
}