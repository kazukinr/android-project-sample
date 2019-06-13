package com.github.kazukinr.android.sample.ui.api_call_sample

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.data.github.dto.Repo
import com.github.kazukinr.android.domain.github.query.GetReposByUser
import com.github.kazukinr.android.domain.github.query.GetReposByUserSuspend
import com.github.kazukinr.android.sample.ui.DisposableLifecycleObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ApiCallSampleViewModel @Inject constructor(
    private val getReposByUser: GetReposByUser,
    private val getReposByUserSuspend: GetReposByUserSuspend
) : ViewModel(),
    DisposableLifecycleObserver.Callbacks,
    ApiCallSampleView.Listener {

    val headerData = ApiCallSampleViewHeaderData()
    val repos = ObservableField<List<Repo>>()

    override fun onSearchUserReposRxClicked() {
        headerData.inputUserId.also {
            if (it?.isNotEmpty() == true) {
                getReposByUser(it)
                    .subscribe({
                        repos.set(it)
                    }, Timber::e)
            }
        }
    }

    override fun onSearchUserReposCoroutineClicked() {
        headerData.inputUserId.also {
            if (it?.isNotEmpty() == true) {
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        withContext(Dispatchers.Default) {
                            getReposByUserSuspend(it)
                        }.also {
                            repos.set(it)
                        }
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
        }
    }
}
