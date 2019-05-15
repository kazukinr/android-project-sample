package com.github.kazukinr.android.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class DatabaseModule {

    @Module
    companion object {

        @JvmStatic
        @Singleton
        @Provides
        fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database.db"
            ).build()
    }
}
