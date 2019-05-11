package com.github.kazukinr.android.sample.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.github.kazukinr.android.sample.di.mapkey.ViewModelKey
import com.github.kazukinr.android.sample.di.scope.FragmentScope
import com.github.kazukinr.android.sample.ui.binding_sample.BindingSampleFragment
import com.github.kazukinr.android.sample.ui.binding_sample.BindingSampleFragmentModule
import com.github.kazukinr.android.sample.ui.top.TopFragment
import com.github.kazukinr.android.sample.ui.top.TopFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {

    @Binds
    fun bindsAppCompatActivity(activity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            TopFragmentModule::class
        ]
    )
    fun contributesTopFragment(): TopFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            BindingSampleFragmentModule::class
        ]
    )
    fun contributesBindingSampleFragment(): BindingSampleFragment
}
