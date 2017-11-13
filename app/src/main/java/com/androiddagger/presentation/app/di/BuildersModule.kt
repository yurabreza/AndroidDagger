package com.androiddagger.presentation.app.di

import com.androiddagger.presentation.screens.MainActivity
import com.androiddagger.presentation.screens.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
 abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}