package com.github.kazukinr.android.data.user.dao

import androidx.room.*
import com.github.kazukinr.android.data.user.entity.User
import io.reactivex.Flowable

@Dao
internal interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<User>>

    @Insert
    fun add(vararg users: User)

    @Update
    fun update(vararg users: User)

    @Delete
    fun delete(vararg users: User)
}