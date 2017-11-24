package com.androiddagger.presentation.screens.di

import com.androiddagger.data.DataService
import com.androiddagger.data.HelloDataService
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides fun provideDataService(dataService: DataService) = HelloDataService(dataService)
}