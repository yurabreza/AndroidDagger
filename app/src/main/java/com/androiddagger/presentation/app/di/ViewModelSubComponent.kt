package com.androiddagger.presentation.app.di

import com.androiddagger.presentation.screens.detail.DetailViewModel
import com.androiddagger.presentation.screens.main.MainViewModel
import dagger.Subcomponent


@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun projectViewModel(): MainViewModel
    fun detailViewModel(): DetailViewModel
}