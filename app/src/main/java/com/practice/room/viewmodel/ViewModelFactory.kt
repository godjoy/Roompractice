package com.practice.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.room.data.repository.DogRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val appsRepository: DogRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(appsRepository)
                isAssignableFrom(DogsViewModel::class.java) ->
                    DogsViewModel(appsRepository)
                isAssignableFrom(DogViewModel::class.java) ->
                    DogViewModel(appsRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}