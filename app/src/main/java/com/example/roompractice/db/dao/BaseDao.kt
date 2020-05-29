package com.example.roompractice.db.dao

import androidx.room.*
import io.reactivex.Completable

interface BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Entity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Array<Entity>): Completable

    @Update
    fun update(entity: Entity): Completable

    @Delete
    fun delete(entity: Entity): Completable
}