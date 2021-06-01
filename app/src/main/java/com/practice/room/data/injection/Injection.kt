package com.practice.room.data.injection

import android.content.Context
import com.practice.room.data.remote.ApiProvider
import com.practice.room.data.repository.DogRepository
import com.practice.room.data.repository.DogRepositoryImpl
import com.practice.room.data.room.DogDatabase

object Injection {
    fun provideDogRepository(context: Context): DogRepository {
        return DogRepositoryImpl(
            DogDatabase.getDatabase(context).dogDao(),
            ApiProvider.provideDogApi(),
        )
    }
}