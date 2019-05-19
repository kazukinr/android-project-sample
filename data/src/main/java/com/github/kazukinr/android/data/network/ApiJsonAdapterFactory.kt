package com.github.kazukinr.android.data.network

import com.github.kazukinr.android.data.github.dto.Repo
import com.github.kazukinr.android.data.github.dto.RepoJsonAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Reusable
import java.lang.reflect.Type
import javax.inject.Inject

@Reusable
class ApiJsonAdapterFactory @Inject constructor() : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? =
        when (type) {
            Repo::class.java -> RepoJsonAdapter(moshi)
            else -> null
        }
}
