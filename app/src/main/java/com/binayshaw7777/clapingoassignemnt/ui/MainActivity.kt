package com.binayshaw7777.clapingoassignemnt.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.binayshaw7777.clapingoassignemnt.R
import com.binayshaw7777.clapingoassignemnt.R.id.*
import com.binayshaw7777.clapingoassignemnt.databinding.ActivityMainBinding
import com.binayshaw7777.clapingoassignemnt.model.ApiRequest
import com.binayshaw7777.clapingoassignemnt.model.Teacher
import com.binayshaw7777.clapingoassignemnt.model.Timeslot
import com.binayshaw7777.clapingoassignemnt.utils.Logger
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerViewAdapter: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        initObservers()
    }

    private fun initVars() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.performJsonParsing(applicationContext.assets)

    }

    private fun initObservers() {
        mainViewModel.run {
            successLiveData.observe(this@MainActivity) {
                Logger.debugLog(it.toString())
                updateData(it)
            }
            failureLiveData.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(apiRequest: ApiRequest) {
        apiRequest.apply {
            if (statusCode != 200) return

            this.teacher?.let { updateTeacherDetails(it, apiRequest.rating ?: 0.0) }
            if (bookedTimings != null && timeslot != null) {
                updateTimeSlots(bookedTimings!!, timeslot!!)
            }
        }
    }

    private fun updateTimeSlots(bookedTimings: Timeslot, timeslot: Timeslot) {

    }

    private fun updateTeacherDetails(teacher: Teacher, rating: Double) {
        binding.teacherNameTextView.text = teacher.name.toString()
        Logger.debugLog("Teacher name is: ${teacher.name}")
        Glide.with(this@MainActivity).load(teacher.image).diskCacheStrategy(
            DiskCacheStrategy.DATA
        ).into(binding.teacherImageView)
        binding.ratingIncludeLayout.ratingTextView.text = rating.toString()
    }
}