package com.practice.room.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogBreed (
    @PrimaryKey
    val id: Long,
    val breed: String
)