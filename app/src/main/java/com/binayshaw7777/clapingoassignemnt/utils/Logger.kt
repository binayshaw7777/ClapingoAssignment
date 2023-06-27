package com.binayshaw7777.clapingoassignemnt.utils

import android.util.Log

object Logger {

    fun debugLog(msg: String?) {
        Log.d("", msg!!)
    }

    fun Any.debugLog(tag: String? = "DEBUG_TAG") {
        Log.d(tag, toString())
    }


    fun logException(
        tag: String,
        exception: Exception,
        logLevel: LogLevel,
        logToCrashlytics: Boolean = false
    ) {
        when (logLevel) {
            LogLevel.DEBUG -> Log.d(tag, null, exception)
            LogLevel.ERROR -> Log.e(tag, null, exception)
            LogLevel.INFO -> Log.i(tag, null, exception)
            LogLevel.VERBOSE -> Log.v(tag, null, exception)
            LogLevel.WARN -> Log.w(tag, null, exception)
        }
        if (logToCrashlytics) {
            //TODO: send log to crashlytics like Firebase
        }
    }

    enum class LogLevel {
        DEBUG, ERROR, INFO, VERBOSE, WARN
    }
}