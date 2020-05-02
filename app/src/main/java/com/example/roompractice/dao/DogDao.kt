package com.example.roompractice.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roompractice.entity.Dog

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDog(dog: Dog)

    @Delete
    fun deleteDog(dog: Dog)

    @Update
    fun updateDog(dog: Dog)

    @Query("SELECT * from dog")
    fun getDog() : LiveData<MutableList<Dog>>

    @Query("SELECT * from dog WHERE name = :name")
    fun getDogByName(name: String): LiveData<MutableList<Dog>>

}