package com.github.kazukinr.android.sample.ui.room_rx_sample

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.data.user.entity.User
import com.github.kazukinr.android.domain.user.command.AddUser
import com.github.kazukinr.android.domain.user.command.DeleteUser
import com.github.kazukinr.android.domain.user.command.UpdateUser
import com.github.kazukinr.android.domain.user.query.ObserveUsers
import com.github.kazukinr.android.sample.ui.DisposableLifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RoomRxSampleViewModel @Inject constructor(
    private val observeUsers: ObserveUsers,
    private val addUser: AddUser,
    private val updateUser: UpdateUser,
    private val deleteUser: DeleteUser
) : ViewModel(),
    DisposableLifecycleObserver.Callbacks,
    RoomRxSampleView.Listener {

    val headerData = RoomRxSampleViewHeaderData()
    val users = ObservableField<List<User>>()

    override fun onStart(disposable: CompositeDisposable) {

        observeUsers()
            .subscribe({
                users.set(it)
            }, Timber::e)
            .let(disposable::add)
    }

    override fun onAddUserClicked() {
        headerData.inputUserName.also {
            if (it?.isNotEmpty() == true) {
                GlobalScope.launch {
                    addUser(it).await()
                }
            }
        }
    }
}
