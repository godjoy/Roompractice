package com.practice.room.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.room.data.repository.DogRepository
import com.practice.room.data.room.DogBreed

class AddDogViewModel(private val repository: DogRepository) : ViewModel() {
    private val _breedList = MutableLiveData<List<DogBreed>>()
    val breedList : LiveData<List<DogBreed>>
        get() = _breedList

    init {
        getBreedList()
    }

    private fun getBreedList() {
        _breedList.value = repository.getBreedList()
    }
}