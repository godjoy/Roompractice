package com.example.roompractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roompractice.dao.DogDao
import com.example.roompractice.entity.Dog

@Database(entities = [Dog::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                instance
                    ?: buildDatabase(
                        context
                    )
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_db")
                .build()
        }
    }
}