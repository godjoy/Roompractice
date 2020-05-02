package com.example.roompractice.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roompractice.entity.Dog
import com.example.roompractice.repository.Repository

class MainViewModel(application: Application) : ViewModel() {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }

    private val repository: Repository = Repository(application)

    private val dogs: LiveData<MutableList<Dog>> by lazy {
        repository.getDog()
    }

    fun getDog() = dogs

    fun getDogByName(name: String) = repository.getDogByName(name)

    fun insertDog(dog: Dog) {
        repository.insertDog(dog)
    }

    fun deleteDog(dog: Dog) {
        repository.deleteDog(dog)
    }
}