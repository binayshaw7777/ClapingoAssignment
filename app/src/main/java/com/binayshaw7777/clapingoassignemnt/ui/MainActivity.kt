package com.binayshaw7777.clapingoassignemnt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.binayshaw7777.clapingoassignemnt.R
import com.binayshaw7777.clapingoassignemnt.databinding.ActivityMainBinding
import com.binayshaw7777.clapingoassignemnt.model.ApiRequest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerViewAdapter: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        initVars()
        initObservers()
    }

    private fun initVars() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun initObservers() {
        mainViewModel.run {
            performJsonParsing(applicationContext.assets)
            successLiveData.observe(this@MainActivity) {
                updateData(it)
            }
            failureLiveData.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(apiRequest: ApiRequest) {

    }
}