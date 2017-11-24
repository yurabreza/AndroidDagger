package com.androiddagger.presentation.screens.main

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.androiddagger.data.DataService
import com.androiddagger.presentation.utils.disableAfterClick
import javax.inject.Inject

class MainViewModel : ViewModel() {
    val message = ObservableField<String>("primary value")
    @Inject lateinit var dataService: DataService

    init {
        dataService.sayHello()
    }

    fun onClick(view: View) {
        view.disableAfterClick()
        message.set(dataService.sayHello())
    }
}

