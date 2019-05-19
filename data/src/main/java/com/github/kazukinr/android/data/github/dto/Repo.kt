package com.github.kazukinr.android.data.github.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    val id: String,
    val name: String,
    val description: String?
)