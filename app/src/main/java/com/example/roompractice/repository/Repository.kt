package com.example.roompractice.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.roompractice.db.AppDatabase
import com.example.roompractice.db.dao.DogDao
import com.example.roompractice.db.entity.Dog
import io.reactivex.Completable
import io.reactivex.Single

class Repository (application: Application) {

    private val dogDao: DogDao by lazy {
        val db = AppDatabase.getInstance(application)
        db.dogDao()
    }

    private val dogs: LiveData<MutableList<Dog>> by lazy {
        dogDao.getDog()
    }

    fun getDog(): LiveData<MutableList<Dog>> {
        return dogs
    }

    fun getDogByName(name: String): LiveData<MutableList<Dog>> {
        return dogDao.getDogByName(name)
    }
    fun insertDog(dog: Dog): Completable = dogDao.insert(dog)

    fun deleteDog(dog: Dog): Completable = dogDao.delete(dog)

    fun updateDog(dog: Dog): Completable = dogDao.update(dog)

}
