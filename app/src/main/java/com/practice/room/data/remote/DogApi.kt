package com.practice.room.data.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breed/{breed}/images/random")
    fun getDogImage(@Path("breed") breed: String): Single<DogResponse>
}

data class DogResponse (val message: String, val status: String)
