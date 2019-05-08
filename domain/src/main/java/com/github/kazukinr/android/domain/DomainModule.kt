package com.github.kazukinr.android.domain

import com.github.kazukinr.android.data.DataModule
import dagger.Module

@Module(
    includes = [
        DataModule::class
    ]
)
interface DomainModule {
}
