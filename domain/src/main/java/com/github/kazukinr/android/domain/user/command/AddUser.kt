package com.github.kazukinr.android.domain.user.command

import com.github.kazukinr.android.data.user.UserRepository
import com.github.kazukinr.android.data.user.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

interface AddUser {

    operator fun invoke(name: String): Deferred<Unit>
}

internal class AddUserImpl @Inject constructor(
    private val userRepository: UserRepository
) : AddUser {

    override fun invoke(name: String): Deferred<Unit> =
        CoroutineScope(Dispatchers.Default).async {
            userRepository.add(User(id = 0L, name = name))
        }
}
