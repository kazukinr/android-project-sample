package com.github.kazukinr.android.domain.user.command

import com.github.kazukinr.android.data.user.UserRepository
import com.github.kazukinr.android.data.user.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

interface DeleteUser {

    operator fun invoke(id: Long): Deferred<Unit>
}

internal class DeleteUserImpl @Inject constructor(
    private val userRepository: UserRepository
) : DeleteUser {

    override fun invoke(id: Long): Deferred<Unit> =
        CoroutineScope(Dispatchers.Default).async {
            userRepository.delete(User(id = id, name = null))
        }
}
