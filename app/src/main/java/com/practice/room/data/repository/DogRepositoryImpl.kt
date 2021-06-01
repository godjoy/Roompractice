package com.practice.room.data.repository

import com.practice.room.data.remote.DogApi
import com.practice.room.data.remote.DogResponse
import com.practice.room.data.room.Dog
import com.practice.room.data.room.DogDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DogRepositoryImpl(
    private val dogDao: DogDao, private val dogApi: DogApi)
    : DogRepository {

    // local
    override fun getDogById(id: Long): Single<Dog> =
        dogDao.getDogById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getDogs(): Single<List<Dog>> =
        dogDao.getDogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    // remote
    override fun getDogImage(breed: String): Single<DogResponse> =
        dogApi.getDogImage(breed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}