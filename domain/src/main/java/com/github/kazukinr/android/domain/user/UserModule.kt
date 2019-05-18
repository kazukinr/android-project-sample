package com.github.kazukinr.android.domain.user

import com.github.kazukinr.android.domain.user.command.*
import com.github.kazukinr.android.domain.user.query.ObserveUsers
import com.github.kazukinr.android.domain.user.query.ObserveUsersImpl
import dagger.Binds
import dagger.Module

@Module
internal interface UserModule {

    @Binds
    fun bindsObserveUsers(impl: ObserveUsersImpl): ObserveUsers

    @Binds
    fun bindsAddUser(impl: AddUserImpl): AddUser

    @Binds
    fun bindsUpdateUser(impl: UpdateUserImpl): UpdateUser

    @Binds
    fun bindsDeleteUser(impl: DeleteUserImpl): DeleteUser
}