package com.practice.room.ui

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.practice.room.R
import com.practice.room.data.injection.Injection
import com.practice.room.util.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(Injection.provideDogRepository(this)))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.isDataLoaded.observe(this, {
            if (it) {
                val host: NavHostFragment = supportFragmentManager
                    .findFragmentById(R.id.navFragment) as NavHostFragment

                val navController = host.navController

                navController.addOnDestinationChangedListener { _, desination, _ ->
                    val dest: String = try {
                        resources.getResourceName(desination.id)
                    } catch (e: Resources.NotFoundException) {
                        desination.id.toString()
                    }

                    Toast.makeText(this@MainActivity, "Navigated to $dest",
                        Toast.LENGTH_LONG).show()
                    Log.d("MainActivity", "Navigated to $dest")
                }
            }
        })
    }
}