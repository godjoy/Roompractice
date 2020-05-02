package com.example.roompractice.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.roompractice.AppDatabase
import com.example.roompractice.dao.DogDao
import com.example.roompractice.entity.Dog

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
    fun insertDog(dog: Dog) {
        InsertDogAsyncTask(dogDao).execute(dog)
    }

    fun deleteDog(dog: Dog) {
        DeleteDogAsyncTask(dogDao).execute(dog)
    }

    private class InsertDogAsyncTask(val dogDao: DogDao) : AsyncTask<Dog, Unit, Unit>() {

        override fun doInBackground(vararg d: Dog) {
            dogDao.insertDog(d[0])
        }
    }

    private class DeleteDogAsyncTask(val dogDao: DogDao): AsyncTask<Dog, Unit, Unit>() {

        override fun doInBackground(vararg d: Dog) {
            dogDao.deleteDog(d[0])
        }
    }

}
