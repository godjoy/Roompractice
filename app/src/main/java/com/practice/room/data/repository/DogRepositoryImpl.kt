package com.practice.room.data.repository

import com.practice.room.data.remote.DogApi
import com.practice.room.data.remote.DogResponse
import com.practice.room.data.room.Dog
import com.practice.room.data.room.DogBreed
import com.practice.room.data.room.DogDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class DogRepositoryImpl(
    private val dogDao: DogDao, private val dogApi: DogApi)
    : DogRepository {

    // local
    override fun addDog(data: Dog) : Completable {
        return dogDao.insertDog(data)
    }

    override fun getDogById(id: Long): Single<Dog> = dogDao.getDogById(id)

    override fun getDogs(): Single<List<Dog>> = dogDao.getDogs()

    // TODO : 추후 dao, api 사용
    override fun getBreedList(): List<DogBreed> {
        return listOf(
            DogBreed(0, "affenpinscher"),
            DogBreed(1, "akita"),
            DogBreed(2, "brabancon"),
            DogBreed(3, "chow"),
            DogBreed(4, "dalmatian"),
            DogBreed(5, "eskimo"),
            DogBreed(6, "husky"),
            DogBreed(7, "maltese"),
            DogBreed(8, "mix"),
            DogBreed(9, "pomeranian"),
            DogBreed(10, "saluki")
        )
    }

    // remote
    override fun getDogImage(breed: String): Single<DogResponse> = dogApi.getDogImage(breed)

}