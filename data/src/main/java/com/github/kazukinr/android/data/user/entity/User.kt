package com.github.kazukinr.android.data.user.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String?
)
