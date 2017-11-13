package com.androiddagger.presentation.app.di

import com.androiddagger.data.DataService
import com.androiddagger.presentation.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides fun provideContext(app: App) = app.applicationContext!!

    @Singleton @Provides fun provideDataService() = DataService()

}