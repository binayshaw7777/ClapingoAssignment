package com.binayshaw7777.clapingoassignemnt.utils

import com.binayshaw7777.clapingoassignemnt.model.Timeslot


object Utils {
    fun getListFromDayOfWeek(dayOfWeekIndex: Int, timeslot: Timeslot): ArrayList<String> {
        when (dayOfWeekIndex) {
            0 -> {
                return timeslot.Sunday
            }

            1 -> {
                return timeslot.Monday
            }

            2 -> {
                return timeslot.Tuesday
            }

            3 -> {
                return timeslot.Wednesday
            }

            4 -> {
                return timeslot.Thursday
            }

            5 -> {
                return timeslot.Friday
            }

            6 -> {
                return timeslot.Saturday
            }
        }
        return mutableListOf<String>() as ArrayList<String>
    }
}