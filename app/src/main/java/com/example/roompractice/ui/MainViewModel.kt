package com.example.roompractice.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roompractice.db.entity.Dog
import com.example.roompractice.repository.Repository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : ViewModel() {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }

    private val repository: Repository = Repository(application)
    private val disposable: CompositeDisposable = CompositeDisposable()

    private val dogs: LiveData<MutableList<Dog>> by lazy {
        repository.getDog()
    }

    fun getDog() = dogs

    fun getDogByName(name: String) = repository.getDogByName(name)

    fun insertDog(dog: Dog, next: () -> Unit) {
       repository.insertDog(dog)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
           .apply { disposable.add(this) }
    }

    fun deleteDog(dog: Dog, next: () -> Unit) {
        repository.deleteDog(dog)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
            .apply { disposable.add(this) }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}