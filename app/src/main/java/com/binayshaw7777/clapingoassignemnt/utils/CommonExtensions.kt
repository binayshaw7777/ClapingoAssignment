package com.binayshaw7777.clapingoassignemnt.utils

import android.app.Application
import android.content.Context
import java.io.IOException

fun String.getJsonDataFromAsset(context: Context): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(this).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}