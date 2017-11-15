package com.androiddagger.presentation.app.di

import com.androiddagger.data.di.DataComponent
import com.androiddagger.data.di.DataScope
import com.androiddagger.presentation.app.App
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@DataScope
@Component(dependencies = arrayOf(DataComponent::class),
        modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class))
interface AppComponent {

    fun inject(app: App)
}