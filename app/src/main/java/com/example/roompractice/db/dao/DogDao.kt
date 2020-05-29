package com.example.roompractice.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roompractice.db.entity.Dog
import io.reactivex.Single

@Dao
interface DogDao : BaseDao<Dog> {

    @Query("SELECT * from dog")
    fun getDog() : LiveData<MutableList<Dog>>

    @Query("SELECT * from dog WHERE name = :name")
    fun getDogByName(name: String): LiveData<MutableList<Dog>>

}