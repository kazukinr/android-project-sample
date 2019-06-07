package com.github.kazukinr.android.domain.github.query

import com.github.kazukinr.android.data.github.GitHubRepository
import com.github.kazukinr.android.data.github.dto.Repo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetReposByUserSuspend {

    suspend operator fun invoke(userId: String): List<Repo>
}

internal class GetReposByUserSuspendImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : GetReposByUserSuspend {

    override suspend fun invoke(userId: String): List<Repo> =
            gitHubRepository.getUserReposSuspend(userId)
}
