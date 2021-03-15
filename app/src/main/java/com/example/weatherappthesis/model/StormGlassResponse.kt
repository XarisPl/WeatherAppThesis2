package com.example.weatherappthesis.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.lang.Exception

@Parcelize
data class StormGlassResponse(
    var locationName: String? = null,
    var hoursList: @RawValue ArrayList<Hour> = arrayListOf()
) : Parcelable {

    constructor(response: StormGlassRawResponse?) : this() {
        if (response == null) return
        val rawHoursList = response.hours as ArrayList
        val tempHoursList = arrayListOf<Hour>()
        rawHoursList.forEach { rawHour ->
            tempHoursList.add(Hour(rawHour))
        }
        locationName = response.locationName
        hoursList = tempHoursList
    }

}

data class Hour(
    var time: String? = null,
    var airTemperature: Double? = null,
    var waveHeight: Double? = null,
    var wavePeriod: Double? = null,
    var windDirection: Double? = null,
    var windSpeed: Double? = null
) {
    constructor(raw: HoursRaw) : this() {
        time = raw.time
        try {
            airTemperature = raw.airTemperatureRaw?.sg?.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            waveHeight = raw.waveHeightRaw?.sg?.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            wavePeriod = raw.wavePeriodRaw?.sg?.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            windDirection = raw.windDirectionRaw?.sg?.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            windSpeed = raw.windSpeedRaw?.sg?.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}