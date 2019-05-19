package com.github.kazukinr.android.data

import com.github.kazukinr.android.data.database.DatabaseModule
import com.github.kazukinr.android.data.github.GitHubModule
import com.github.kazukinr.android.data.network.NetworkModule
import com.github.kazukinr.android.data.user.UserModule
import dagger.Module

@Module(
    includes = [
        DatabaseModule::class,
        NetworkModule::class,
        UserModule::class,
        GitHubModule::class
    ]
)
interface DataModule