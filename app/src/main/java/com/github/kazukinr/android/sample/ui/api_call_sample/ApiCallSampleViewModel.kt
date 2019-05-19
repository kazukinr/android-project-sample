package com.github.kazukinr.android.sample.ui.api_call_sample

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.data.github.dto.Repo
import com.github.kazukinr.android.domain.github.query.GetReposByUser
import com.github.kazukinr.android.sample.ui.DisposableLifecycleObserver
import timber.log.Timber
import javax.inject.Inject

class ApiCallSampleViewModel @Inject constructor(
    private val getReposByUser: GetReposByUser
) : ViewModel(),
    DisposableLifecycleObserver.Callbacks,
    ApiCallSampleView.Listener {

    val headerData = ApiCallSampleViewHeaderData()
    val repos = ObservableField<List<Repo>>()

    override fun onSearchUserReposClicked() {
        headerData.inputUserId.also {
            if (it?.isNotEmpty() == true) {
                getReposByUser(it)
                    .subscribe({
                        repos.set(it)
                    }, Timber::e)
            }
        }
    }
}
