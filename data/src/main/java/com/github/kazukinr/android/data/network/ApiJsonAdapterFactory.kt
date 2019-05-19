package com.github.kazukinr.android.data.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Reusable
import java.lang.reflect.Type
import javax.inject.Inject

@Reusable
class ApiJsonAdapterFactory @Inject constructor() : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}