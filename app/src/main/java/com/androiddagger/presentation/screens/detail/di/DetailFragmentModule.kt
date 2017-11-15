package com.androiddagger.presentation.screens.detail.di

import com.androiddagger.data.DetailFragmentService
import dagger.Module
import dagger.Provides

@Module
class DetailFragmentModule {
    @Provides fun provideDetailFragmentModule() = DetailFragmentService()
}

