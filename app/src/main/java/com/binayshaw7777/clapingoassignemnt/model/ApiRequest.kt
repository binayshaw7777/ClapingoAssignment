package com.binayshaw7777.clapingoassignemnt.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiRequest(
    @SerializedName("statusCode") var statusCode: Int? = null,
    @SerializedName("teacher") var teacher: Teacher? = Teacher(),
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("isBlocked") var isBlocked: Boolean? = null,
    @SerializedName("timeslot") var timeslot: Timeslot? = Timeslot(),
    @SerializedName("bookedTimings") var bookedTimings: Timeslot? = Timeslot()
) : Parcelable

@Parcelize
data class Teacher(
    @SerializedName("isYoutubeConsentSigned") var isYoutubeConsentSigned: Boolean? = null,
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image") var image: String? = null
) : Parcelable

@Parcelize
data class Timeslot(
    @SerializedName("Sunday") var Sunday: ArrayList<String> = arrayListOf(),
    @SerializedName("Monday") var Monday: ArrayList<String> = arrayListOf(),
    @SerializedName("Tuesday") var Tuesday: ArrayList<String> = arrayListOf(),
    @SerializedName("Wednesday") var Wednesday: ArrayList<String> = arrayListOf(),
    @SerializedName("Thursday") var Thursday: ArrayList<String> = arrayListOf(),
    @SerializedName("Friday") var Friday: ArrayList<String> = arrayListOf(),
    @SerializedName("Saturday") var Saturday: ArrayList<String> = arrayListOf()
) : Parcelable

@Parcelize
data class BookedTimings(
    @SerializedName("Sunday") var Sunday: ArrayList<String> = arrayListOf(),
    @SerializedName("Monday") var Monday: ArrayList<String> = arrayListOf(),
    @SerializedName("Tuesday") var Tuesday: ArrayList<String> = arrayListOf(),
    @SerializedName("Wednesday") var Wednesday: ArrayList<String> = arrayListOf(),
    @SerializedName("Thursday") var Thursday: ArrayList<String> = arrayListOf(),
    @SerializedName("Friday") var Friday: ArrayList<String> = arrayListOf(),
    @SerializedName("Saturday") var Saturday: ArrayList<String> = arrayListOf()
) : Parcelable
