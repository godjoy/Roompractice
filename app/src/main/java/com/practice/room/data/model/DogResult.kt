package com.practice.room.data.model

import com.practice.room.data.room.Dog

sealed class DogResult {
    data class Success(val data: List<Dog>): DogResult()
    data class Error(val error: Throwable) : DogResult()
    object InProgress: DogResult()
}