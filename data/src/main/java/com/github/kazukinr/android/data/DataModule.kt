package com.github.kazukinr.android.data

import com.github.kazukinr.android.data.database.DatabaseModule
import com.github.kazukinr.android.data.user.UserModule
import dagger.Module

@Module(
    includes = [
        DatabaseModule::class,
        UserModule::class
    ]
)
interface DataModule