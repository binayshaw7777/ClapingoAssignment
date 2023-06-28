package com.binayshaw7777.clapingoassignemnt.utils


import android.content.res.AssetManager
import android.view.View
import com.binayshaw7777.clapingoassignemnt.model.ApiRequest
import com.binayshaw7777.clapingoassignemnt.network.RequestStatus
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.InputStream

fun <T> createSublistIfSizeExceedsThreshold(list: ArrayList<T>, threshold: Int): MutableList<T> {
    return if (list.size > threshold) {
        list.subList(0, threshold)
    } else {
        list
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun parseJsonFile(assetManager: AssetManager): RequestStatus {
    val fileName = Constants.fileName

    return try {
        val inputStream: InputStream = assetManager.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val dataClass = gson.fromJson(jsonString, ApiRequest::class.java)
        RequestStatus.Success(dataClass)
    } catch (exception: JsonSyntaxException) {
        RequestStatus.Failure("Invalid JSON format")
    } catch (exception: Exception) {
        RequestStatus.Failure(exception.message ?: "Unknown error occurred")
    }
}
