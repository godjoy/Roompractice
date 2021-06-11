package com.practice.room.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.practice.room.data.model.DogResult
import com.practice.room.data.repository.DogRepository
import com.practice.room.data.room.Dog
import com.practice.room.util.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val dogRepository: DogRepository): BaseViewModel() {

    init {
        addDogs()
    }

    private fun addDogs() {
        dogRepository.addDog(Dog(0, "test", "testBreed", "null"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                inProgressLoadData()
            }
            .subscribe({
                finishLoadData()
            }, {
                finishLoadData()
            })
    }
}