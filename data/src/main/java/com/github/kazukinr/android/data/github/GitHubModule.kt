package com.github.kazukinr.android.data.github

import com.github.kazukinr.android.data.github.api.GitHubApi
import com.github.kazukinr.android.data.github.api.GitHubApiImpl
import dagger.Binds
import dagger.Module

@Module
internal interface GitHubModule {

    @Binds
    fun bindsGitHubApi(impl: GitHubApiImpl): GitHubApi
}
