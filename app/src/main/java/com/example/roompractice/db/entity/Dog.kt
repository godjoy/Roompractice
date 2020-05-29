package com.example.roompractice.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Dog(
    @PrimaryKey(autoGenerate = true) val Id: Long?,
    val address: String,
    val name: String,
    val breed: String,
    val cuteness: String
)

