package com.practice.room.data.repository

import com.practice.room.data.remote.DogResponse
import com.practice.room.data.room.Dog
import io.reactivex.rxjava3.core.Single

interface DogRepository {
    fun getDogById(id: Long): Single<Dog>
    fun getDogs(): Single<List<Dog>>
    fun getDogImage(breed: String): Single<DogResponse>
}