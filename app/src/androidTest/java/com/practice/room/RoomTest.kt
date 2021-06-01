package com.practice.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.practice.room.data.room.Dog
import com.practice.room.data.room.DogDao
import com.practice.room.data.room.DogDatabase
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class RoomTest {
    private lateinit var dogDao: DogDao
    private lateinit var db: DogDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DogDatabase::class.java).build()
        dogDao = db.dogDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadTest() {
        val testData = Dog(0, "mung-mung", "redbone", "https://images.dog.ceo/breeds/redbone/n02090379_4683.jpg")

        // insert
        dogDao.insertDog(testData)

        // read dogs
        dogDao.getDogs().subscribeWith(object : DisposableSingleObserver<List<Dog>>() {
            override fun onSuccess(dogs: List<Dog>?) {
                dogs?.forEach{ dog -> Log.d("Room write and Read Test", "dog: $dog")}
            }

            override fun onError(e: Throwable?) {
                Log.e("Room write and Read Test", "error: ${e?.message}")
            }
        })
    }

    @Test
    @Throws(Exception::class)
    fun deleteTest() {
        val deleteTestData = Dog(0, "phone_mung_mung", "redbone", "https://images.dog.ceo/breeds/redbone/n02090379_4683.jpg")

        dogDao.insertDog(deleteTestData)

        // read dog by id
        dogDao.getDogById(1).subscribe(
            {
                Log.d("Room read test with id", "dog: $it")
            },
            {
                Log.e("Room write and Read Test", "error: ${it.message}")
            }
        )

        dogDao.deleteDogById(1)

        dogDao.getDogById(1).subscribe(
            {},
            {
                Log.e("Room delete Test", "error: ${it.message}")
            }
        )
    }

}