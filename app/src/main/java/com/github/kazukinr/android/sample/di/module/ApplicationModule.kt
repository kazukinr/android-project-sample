package com.github.kazukinr.android.sample.di.module

import android.app.Application
import android.content.Context
import com.github.kazukinr.android.domain.DomainModule
import com.github.kazukinr.android.sample.App
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DomainModule::class
    ]
)
interface ApplicationModule {

    @Binds
    fun bindsApplication(app: App): Application

    @Binds
    fun bindsApplicationContext(application: Application): Context
}
