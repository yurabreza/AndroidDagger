package com.androiddagger.presentation.screens.detail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class DetailViewModel : ViewModel() {
//    lateinit var detailDataService: DetailFragmentService
    val text = ObservableField<String>("")
}