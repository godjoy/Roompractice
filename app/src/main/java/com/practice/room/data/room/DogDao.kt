package com.practice.room.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDog(dog: Dog)

    @Delete
    fun deleteAllDogs(dog: Dog)

    @Update
    fun updateDog(dog: Dog)

    @Query("SELECT * from dog WHERE :id")
    fun getDogById(id: Long) : Single<Dog>

    @Query("SELECT * from dog")
    fun getDogs(): Single<List<Dog>>

    @Query("DELETE FROM dog WHERE id = :id")
    fun deleteDogById(id: Long)
}