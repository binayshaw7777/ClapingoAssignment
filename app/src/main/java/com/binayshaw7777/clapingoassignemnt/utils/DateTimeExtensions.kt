package com.binayshaw7777.clapingoassignemnt.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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

@RequiresApi(Build.VERSION_CODES.O)
fun splitTimeRanges(timeRanges: ArrayList<String>): ArrayList<String> {
    val newRanges = mutableListOf<String>()

    for (range in timeRanges) {
        val parts = range.split("-").map { it.trim() }
        val start = parts[0]
        val end = parts[1]

        val startTime = parseTime(start)
        val endTime = parseTime(end)

        var currentTime = startTime
        while (currentTime.plusMinutes(25) <= endTime) {
            val formattedStartTime = formatTime(currentTime)
            val formattedEndTime = formatTime(currentTime.plusMinutes(25))

            newRanges.add("$formattedStartTime - $formattedEndTime")
            currentTime = currentTime.plusMinutes(30)
        }
    }

    val returnVal: ArrayList<String> = ArrayList()
    returnVal.addAll(newRanges)
    return returnVal
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseTime(timeString: String): LocalTime {
    return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH"))
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTime(time: LocalTime): String {
    return time.format(DateTimeFormatter.ofPattern("HH:mm"))
}

