package com.github.kazukinr.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kazukinr.android.data.user.dao.UserDao
import com.github.kazukinr.android.data.user.entity.User

@Database(
    entities = [
        User::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}