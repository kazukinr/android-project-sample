package com.github.kazukinr.android.domain

import com.github.kazukinr.android.data.DataModule
import com.github.kazukinr.android.domain.user.UserModule
import dagger.Module

@Module(
    includes = [
        DataModule::class,
        UserModule::class
    ]
)
interface DomainModule
