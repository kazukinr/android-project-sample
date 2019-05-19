package com.github.kazukinr.android.data.github

import com.github.kazukinr.android.data.github.api.GitHubApi
import com.github.kazukinr.android.data.github.dto.Repo
import io.reactivex.Single
import javax.inject.Inject

interface GitHubRepository {

    fun getUserRepos(userId: String): Single<List<Repo>>
}

internal class GitHubRepositoryImpl @Inject constructor(
    private val gitHubApi: GitHubApi
) : GitHubRepository {

    override fun getUserRepos(userId: String): Single<List<Repo>> =
        gitHubApi.getUserRepos(userId)
}