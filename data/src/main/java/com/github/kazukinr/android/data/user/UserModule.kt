package com.github.kazukinr.android.data.user

import dagger.Binds
import dagger.Module

@Module
internal interface UserModule {

    @Binds
    fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository
}