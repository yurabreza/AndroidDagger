package com.androiddagger.presentation.app.di

import com.androiddagger.presentation.screens.detail.DetailFragment
import com.androiddagger.presentation.screens.detail.di.DetailFragmentModule
import com.androiddagger.presentation.screens.di.MainActivityModule
import com.androiddagger.presentation.screens.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(DetailFragmentModule::class))
    abstract fun bindDetailFragment(): DetailFragment
}