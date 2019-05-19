package com.github.kazukinr.android.domain.github.query

import com.github.kazukinr.android.data.github.GitHubRepository
import com.github.kazukinr.android.data.github.dto.Repo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetReposByUser {

    operator fun invoke(userId: String): Single<List<Repo>>
}

internal class GetReposByUserImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : GetReposByUser {

    override fun invoke(userId: String): Single<List<Repo>> =
        gitHubRepository.getUserRepos(userId)
            .subscribeOn(Schedulers.io())
}
