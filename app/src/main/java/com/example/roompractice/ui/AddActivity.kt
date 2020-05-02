package com.example.roompractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.roompractice.R
import com.example.roompractice.entity.Dog
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by lazy{
        ViewModelProvider(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnSave.setOnClickListener {
            addDog()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    private fun addDog() {
        val newDog = Dog(null,  etAddress.text.toString(), etDogName.text.toString(), etDogBreed.text.toString(),etDogCuteness.text.toString())
        viewModel.insertDog(newDog)
    }

}
