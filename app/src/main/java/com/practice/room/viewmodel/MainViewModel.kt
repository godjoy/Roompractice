package com.practice.room.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.practice.room.data.repository.DogRepository
import com.practice.room.data.room.Dog
import com.practice.room.util.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val dogRepository: DogRepository): BaseViewModel() {

    init {
        addData()
    }

    fun addData() {
        dogRepository.addDog(Dog(0, "dog1", "boxer", "https://images.dog.ceo/breeds/boxer/n02108089_3412.jpg"))
        dogRepository
            .addDog(Dog(0, "dog2", "akita", "https://images.dog.ceo/breeds/akita/512px-Ainu-Dog.jpg"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                finishLoadData()
            }, {
                Log.d("MainViewModel", "$it")
            })
    }
}