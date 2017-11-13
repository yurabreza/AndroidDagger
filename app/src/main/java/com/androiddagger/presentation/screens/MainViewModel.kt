package com.androiddagger.presentation.screens

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.androiddagger.data.HelloDataService
import com.androiddagger.presentation.utils.disableAfterClick

class MainViewModel : ViewModel() {
    val message = ObservableField<String>("primary value")

    lateinit var dataService: HelloDataService

    fun onClick(view: View) {
        view.disableAfterClick()
        message.set(dataService.sayHello())
    }
}

