package com.androiddagger.data

class HelloDataService(private val dataService: DataService) {
    fun sayHello() = dataService.sayHello()

}