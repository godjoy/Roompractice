package com.example.roompractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roompractice.R
import com.example.roompractice.entity.Dog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter { clickEventCallback(it) }
    }
    private lateinit var searchItem : MenuItem
    private lateinit var searchView : SearchView

    private val mainViewModel : MainViewModel by lazy{
        ViewModelProvider(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        init()
        btnAddPerson.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun init() {
        getDog()
        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.serch_menu, menu)
        searchItem = menu.findItem(R.id.app_bar_search)
        searchView = searchItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = "이름으로 검색"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(new: String?): Boolean {
                if(new.isNullOrBlank()){
                    getDog()
                }else{
                    getDogByName(new)
                }
                return false
            }
        })
        return true
    }

    private fun getDogByName(q: String) {
        mainViewModel.getDogByName(q).observe(this@MainActivity, Observer<MutableList<Dog>>{
            it?.let{
                mainAdapter.setData(it)
            }
        })
    }
    private fun getDog() {
        mainViewModel.getDog().observe(this, Observer<MutableList<Dog>> {
            it?.let {
               mainAdapter.setData(it)
            }
        })
    }


    private fun initRecycler() {
        rvDog.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL, false)
        }
    }


    private fun clickEventCallback(position: Int){
        val data = mainAdapter.getDataAt(position)
        mainViewModel.deleteDog(data)
    }

}
