package com.practice.room.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.room.data.repository.DogRepository
import com.practice.room.ui.MainViewModel
import com.practice.room.ui.add.AddDogViewModel
import com.practice.room.ui.dog.DogViewModel
import com.practice.room.ui.dogs.DogsViewModel

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
                isAssignableFrom(AddDogViewModel::class.java) ->
                    AddDogViewModel(appsRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}