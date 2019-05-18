package com.github.kazukinr.android.data.user

import com.github.kazukinr.android.data.database.AppDatabase
import com.github.kazukinr.android.data.user.entity.User
import io.reactivex.Flowable
import javax.inject.Inject

interface UserRepository {

    fun observe(): Flowable<List<User>>

    fun add(user: User)

    fun update(user: User)

    fun delete(user: User)
}

internal class UserRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : UserRepository {

    private val userDao by lazy {
        db.userDao()
    }

    override fun observe(): Flowable<List<User>> =
        userDao.getAll()

    override fun add(user: User) {
        userDao.add(user)
    }

    override fun update(user: User) {
        userDao.update(user)
    }

    override fun delete(user: User) {
        userDao.delete(user)
    }
}