package com.github.kazukinr.android.domain.user.command

import com.github.kazukinr.android.data.user.UserRepository
import com.github.kazukinr.android.data.user.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

interface UpdateUser {

    operator fun invoke(id: Long, name: String): Deferred<Unit>
}

internal class UpdateUserImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUser {

    override fun invoke(id: Long, name: String): Deferred<Unit> =
        CoroutineScope(Dispatchers.Default).async {
            userRepository.update(User(id = id, name = name))
        }
}
