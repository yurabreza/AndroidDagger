package com.androiddagger.presentation.screens

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androiddagger.R
import com.androiddagger.data.HelloDataService
import com.androiddagger.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var dataService: HelloDataService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.dataService = dataService
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).model = viewModel


    }
}
