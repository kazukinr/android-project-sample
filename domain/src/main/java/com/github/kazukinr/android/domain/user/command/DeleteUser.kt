package com.github.kazukinr.android.domain.user.command

import com.github.kazukinr.android.data.user.UserRepository
import com.github.kazukinr.android.data.user.entity.User
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

interface DeleteUser {

    operator fun invoke(id: Long): Deferred<Unit>
}

internal class DeleteUserImpl @Inject constructor(
    private val userRepository: UserRepository
) : DeleteUser {

    override fun invoke(id: Long): Deferred<Unit> = GlobalScope.async {
        userRepository.delete(User(id = id, name = null))
    }
}
