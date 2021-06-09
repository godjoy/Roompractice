package com.practice.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.room.data.model.DogResult
import com.practice.room.data.repository.DogRepository
import com.practice.room.util.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class DogsViewModel(private val dogRepository: DogRepository): BaseViewModel() {

    private val _dogs = MutableLiveData<DogResult>()
    val dogs: LiveData<DogResult> = _dogs

    init {
        loadData()
    }

    fun loadData() {
        dogRepository.getDogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                inProgressLoadData()
                _dogs.value = DogResult.InProgress
            }
            .subscribe({
                finishLoadData()
                _dogs.value = DogResult.Success(it)
            }, {
                finishLoadData()
                _dogs.value = DogResult.Error(it)
            })
            .addTo(compositeDisposable)
    }
}