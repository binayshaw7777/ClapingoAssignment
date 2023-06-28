package com.binayshaw7777.clapingoassignemnt.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

val dateFormatter = SimpleDateFormat(Constants.dateFormat, Locale.getDefault())

fun getDayOfWeekString(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        Calendar.SUNDAY -> "Sunday"
        Calendar.MONDAY -> "Monday"
        Calendar.TUESDAY -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY -> "Thursday"
        Calendar.FRIDAY -> "Friday"
        Calendar.SATURDAY -> "Saturday"
        else -> ""
    }
}

fun convertTo12HourFormat(hour: Int, minute: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)

    val dateFormat = SimpleDateFormat(Constants.timeFormat, Locale.getDefault())
    return dateFormat.format(calendar.time)
}