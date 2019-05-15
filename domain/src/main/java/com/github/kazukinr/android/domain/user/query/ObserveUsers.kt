package com.github.kazukinr.android.domain.user.query

import com.github.kazukinr.android.data.user.UserRepository
import com.github.kazukinr.android.data.user.entity.User
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ObserveUsers {

    operator fun invoke(): Flowable<List<User>>
}

internal class ObserveUsersImpl @Inject constructor(
    private val userRepository: UserRepository
) : ObserveUsers {

    override fun invoke(): Flowable<List<User>> =
        userRepository.observe()
            .subscribeOn(Schedulers.io())
}
