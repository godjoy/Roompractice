package com.practice.room

import android.util.Log
import com.practice.room.data.remote.ApiProvider
import org.junit.Test

class RetrofitTest {

    @Test
    fun getDogImageTest() {
        ApiProvider.provideDogApi().getDogImage("samoyed").subscribe(
            {
                Log.d("RetrofitUnitTest", "getDogImage status: ${it.status} imgSrc: ${it.message}")
            },
            {
                Log.d("RetrofitUnitTest", "getDogImage errorMsg: ${it.message}\n  cause: ${it.cause}")
            }
        )
    }
}