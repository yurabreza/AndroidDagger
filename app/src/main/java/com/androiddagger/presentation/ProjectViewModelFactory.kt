package com.androiddagger.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.ArrayMap
import android.util.Log
import com.androiddagger.presentation.app.di.ViewModelSubComponent
import com.androiddagger.presentation.screens.detail.DetailViewModel
import com.androiddagger.presentation.screens.main.MainViewModel
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProjectViewModelFactory @Inject constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {
     val creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    init {
        creators.put(DetailViewModel::class.java, Callable<DetailViewModel> { viewModelSubComponent.detailViewModel() })
        creators.put(MainViewModel::class.java, Callable<MainViewModel> { viewModelSubComponent.projectViewModel() })
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]
        if (creator != null) {

            creator = creators.entries.filter { modelClass.isAssignableFrom(it.key) }[0].value
            Log.d("MyTAg stream", creator.toString())

            for (entry in creators.entries) {
                if (modelClass.isAssignableFrom(entry.key)) {
                    creator = entry.value
                    Log.d("MyTAg", creator.toString())
                    break
                }
            }
        }
        try {
            return creator!!.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}