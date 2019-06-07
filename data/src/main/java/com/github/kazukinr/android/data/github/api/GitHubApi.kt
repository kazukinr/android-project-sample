package com.github.kazukinr.android.data.github.api

import com.github.kazukinr.android.data.github.dto.Repo
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

internal interface GitHubApi {

    fun getUserRepos(userId: String): Single<List<Repo>>

    suspend fun getUserReposSuspend(userId: String): List<Repo>
}

internal class GitHubApiImpl constructor(
    private val service: Service
) : GitHubApi {

    @Inject
    constructor(retrofit: Retrofit) : this(retrofit.create(Service::class.java))

    override fun getUserRepos(userId: String): Single<List<Repo>> =
        service.getUserRepos(userId)

    override suspend fun getUserReposSuspend(userId: String): List<Repo> =
        service.getUserReposSuspend(userId)

    interface Service {

        @GET("users/{user}/repos")
        fun getUserRepos(@Path("user") user: String): Single<List<Repo>>

        @GET("users/{user}/repos")
        suspend fun getUserReposSuspend(@Path("user") user: String): List<Repo>
    }
}
