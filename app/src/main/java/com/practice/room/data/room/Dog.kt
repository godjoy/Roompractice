package com.practice.room.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog")
data class Dog (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val name: String,
    val breed: String,
    val img: String
)