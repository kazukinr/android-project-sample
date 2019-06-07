package com.github.kazukinr.android.domain.github

import com.github.kazukinr.android.domain.github.query.GetReposByUser
import com.github.kazukinr.android.domain.github.query.GetReposByUserImpl
import com.github.kazukinr.android.domain.github.query.GetReposByUserSuspend
import com.github.kazukinr.android.domain.github.query.GetReposByUserSuspendImpl
import dagger.Binds
import dagger.Module

@Module
internal interface GitHubModule {

    @Binds
    fun bindsGetReposByUser(impl: GetReposByUserImpl): GetReposByUser

    @Binds
    fun bindsGetReposByUserSuspend(impl: GetReposByUserSuspendImpl): GetReposByUserSuspend
}