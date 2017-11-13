package com.androiddagger.presentation.app.di

import com.androiddagger.presentation.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class))
 interface AppComponent {

    @Component.Builder
     interface Builder {

        @BindsInstance
         fun application(app: App): Builder

         fun build(): AppComponent
    }

     fun inject(app: App)
}