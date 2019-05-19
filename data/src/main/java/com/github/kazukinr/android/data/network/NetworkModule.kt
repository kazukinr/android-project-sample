package com.github.kazukinr.android.data.network

import com.github.kazukinr.android.data.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import javax.inject.Singleton

@Module
internal abstract class NetworkModule {

    @Module
    companion object {

        @JvmStatic
        @Singleton
        @Provides
        fun providesMoshi(adapterFactory: ApiJsonAdapterFactory): Moshi =
            Moshi.Builder()
                .add(adapterFactory)
                .build()

        @JvmStatic
        @Singleton
        @Provides
        fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                private val TAG = "OkHttp"
                private val PATTERN = Pattern.compile("^<-- ([0-9]{3})")

                override fun log(message: String) {
                    Timber.tag(TAG).v(message)
                    val matcher = PATTERN.matcher(message)
                    if (matcher.find()) {
                        val response = message.replace("<-- ", "")
                        when (matcher.group(1)) {
                            "200", "201", "202", "204" -> Timber.tag(TAG).d(response)
                            else -> Timber.tag(TAG).w(response)
                        }
                    }
                }
            }).also {
                it.level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

        @JvmStatic
        @Singleton
        @Provides
        fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .protocols(mutableListOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
                .build()

        @JvmStatic
        @Singleton
        @Provides
        fun providesRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
