package com.androiddagger.data.di

import com.androiddagger.presentation.app.App
import com.androiddagger.presentation.app.di.AppModule
import com.apollographql.apollo.ApolloClient
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, DataModule::class))
interface DataComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): DataComponent
    }

    fun getApolloClient(): ApolloClient
}