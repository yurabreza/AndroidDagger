package com.androiddagger.presentation.app

import android.app.Activity
import android.app.Application
import com.androiddagger.data.di.DaggerDataComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .dataComponent(DaggerDataComponent.builder().application(this).build())
                .build()
                .inject(this)
    }

}