package com.binayshaw7777.clapingoassignemnt.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.binayshaw7777.clapingoassignemnt.R.id.*
import com.binayshaw7777.clapingoassignemnt.adapter.SlotsAdapter
import com.binayshaw7777.clapingoassignemnt.databinding.ActivityMainBinding
import com.binayshaw7777.clapingoassignemnt.model.ApiRequest
import com.binayshaw7777.clapingoassignemnt.model.Teacher
import com.binayshaw7777.clapingoassignemnt.model.Timeslot
import com.binayshaw7777.clapingoassignemnt.utils.Constants
import com.binayshaw7777.clapingoassignemnt.utils.Constants.recyclerViewThresholdLimitDisplay
import com.binayshaw7777.clapingoassignemnt.utils.Logger
import com.binayshaw7777.clapingoassignemnt.utils.convertTo12HourFormat
import com.binayshaw7777.clapingoassignemnt.utils.createSublistIfSizeExceedsThreshold
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerViewAdapter: SlotsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        initObservers()
        initViews()
    }

    private fun initViews() {
        binding.apply {
            dateCardView.setOnClickListener {
                showDatePicker()
            }
            timeCardView.setOnClickListener {
                showTimePicker()
            }
            val layoutManager = GridLayoutManager(this@MainActivity, 3)
            binding.slotsRecyclerview.layoutManager = layoutManager
            binding.slotsRecyclerview.adapter = recyclerViewAdapter
        }
    }

    private fun initVars() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.performJsonParsing(applicationContext.assets)
        recyclerViewAdapter = SlotsAdapter(this@MainActivity)
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
            selectedDayLiveData.observe(this@MainActivity) {
                showSlotsInRecyclerView(
                    it,
                    successLiveData.value?.timeslot!!,
                    successLiveData.value?.bookedTimings!!,
                    recyclerViewThresholdLimitDisplay
                )
            }
        }
    }

    private fun showSlotsInRecyclerView(
        dayOfWeekIndex: Int,
        timeslot: Timeslot,
        bookedTimings: Timeslot,
        limit: Int
    ) {
        var listOfSlots: ArrayList<String> = ArrayList()
        when (dayOfWeekIndex) {
            0 -> {
                listOfSlots = timeslot.Sunday
            }

            1 -> {
                listOfSlots = timeslot.Monday
            }

            2 -> {
                listOfSlots = timeslot.Tuesday
            }

            3 -> {
                listOfSlots = timeslot.Wednesday
            }

            4 -> {
                listOfSlots = timeslot.Thursday
            }

            5 -> {
                listOfSlots = timeslot.Friday
            }

            6 -> {
                listOfSlots = timeslot.Saturday
            }
        }
        Logger.debugLog("Initial List items: $listOfSlots")
        val trimmedSlotsList = createSublistIfSizeExceedsThreshold(listOfSlots, limit)
        recyclerViewAdapter.setAllItems(trimmedSlotsList)
    }

    private fun updateData(apiRequest: ApiRequest) {
        apiRequest.apply {
            if (statusCode != 200) return
            this.teacher?.let { updateTeacherDetails(it, apiRequest.rating ?: 0.0) }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun splitInto15MinIntervals(array: Array<String>): List<List<String>> {
        val localTimes = array.map { LocalTime.parse(it) }
        val intervals = mutableListOf<LocalTime>()
        var time = localTimes.first()
        while (time.isBefore(localTimes.last())) {
            intervals.add(time)
            time = time.plusMinutes(15)
        }
        intervals.add(localTimes.last())
        val chunkSize = intervals.size / 3
        return intervals.chunked(chunkSize).map { chunk ->
            chunk.map { it.toString() }
        }
    }

    private fun updateTeacherDetails(teacher: Teacher, rating: Double) {
        binding.teacherNameTextView.text = teacher.name.toString()
        Logger.debugLog("Teacher name is: ${teacher.name}")
        Glide.with(this@MainActivity).load(teacher.image).diskCacheStrategy(
            DiskCacheStrategy.DATA
        ).into(binding.teacherImageView)
        binding.ratingIncludeLayout.ratingTextView.text = rating.toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().apply {
            val constraintsBuilder = CalendarConstraints.Builder()
            setCalendarConstraints(constraintsBuilder.build())
            val calendar = Calendar.getInstance()
            setSelection(calendar.timeInMillis)
        }.build()
        datePicker.show(supportFragmentManager, "DatePicker")

        datePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat(Constants.dateFormat)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it

            val date = dateFormatter.format(calendar.time)
            val day = calendar.time.day

            binding.selectedDateTextView.text = date
            mainViewModel.setSelectedDate(date, day)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showTimePicker() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(hour)
            .setMinute(minute)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute
            val timeDisplay = convertTo12HourFormat(selectedHour, selectedMinute)
            binding.selectedTimeTextView.text = timeDisplay
            mainViewModel.setSelectedTime(timeDisplay)
        }
        timePicker.show(supportFragmentManager, "timePicker")
    }
}