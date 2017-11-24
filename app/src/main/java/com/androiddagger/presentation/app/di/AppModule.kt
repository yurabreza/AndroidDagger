package com.androiddagger.presentation.app.di

import com.androiddagger.data.DataService
import com.androiddagger.presentation.ProjectViewModelFactory
import com.androiddagger.presentation.app.App
import dagger.Module
import dagger.Provides

@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule {

    @Provides fun provideContext(app: App) = app.applicationContext!!

    @Provides fun provideDataService() = DataService()

    @Provides fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder) = ProjectViewModelFactory(viewModelSubComponent.build())
}