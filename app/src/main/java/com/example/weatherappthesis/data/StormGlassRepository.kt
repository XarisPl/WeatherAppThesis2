package com.example.weatherappthesis.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappthesis.ApiResponse
import com.example.weatherappthesis.BuildConfig
import com.example.weatherappthesis.Constants.Companion.EMPTY
import com.example.weatherappthesis.OpenWeatherApi
import com.example.weatherappthesis.model.*
import com.example.weatherappthesis.network.StormGlassApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.text.SimpleDateFormat
import java.util.*

class StormGlassRepository(private val api: StormGlassApi) {

    companion object {
        private const val HTTP_STATUS_OK = 200
        private const val HTTP_STATUS_CREATED = 201
        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val PARAM_LIST = "airTemperature,waveHeight,wavePeriod,windDirection,windSpeed"
        private const val SG_SOURCE_KEY = "sg"
    }

    fun getWeather(lat: String, lon:String, name: String, isMocked: Boolean = false): LiveData<ApiResponse<StormGlassResponse>> {
        val result = MutableLiveData<ApiResponse<StormGlassResponse>>()

        if (isMocked) {
            result.postValue(ApiResponse.success(createMockedResponse(name)))
            return result
        }

        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        var newDate = Date()
        calendar.time = newDate
        calendar.add(Calendar.DATE, 1)
        newDate = calendar.time
        val startDate = dateFormat.format(Date(System.currentTimeMillis()))
        val endDate = dateFormat.format(newDate)

        GlobalScope.launch {
            try {
                val apiResponse = api.getWeather(BuildConfig.STORM_GLASS_API_KEY, lat, lon, listOf(PARAM_LIST), startDate, endDate, SG_SOURCE_KEY).awaitResponse()
                when (apiResponse.code()) {
                    HTTP_STATUS_OK, HTTP_STATUS_CREATED -> {
                        val body = apiResponse.body()
                        body?.locationName = name
                        result.postValue(ApiResponse.success(body))
                    }
                    else -> {
                        result.postValue(
                            ApiResponse.error(
                                0
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                result.postValue(
                    ApiResponse.error(
                        0,
                        e.message
                    )
                )
            }
        }
        return result
    }

    private fun createMockedResponse(name: String): StormGlassResponse {
        val mockedResponse = StormGlassResponse()
        val mockedHours = arrayListOf<Hours>()
        val airTemp1 = AirTemperature()
        airTemp1.sg = "2.2"
        val waveHeight1 = WaveHeight()
        waveHeight1.sg = "2.2"
        val wavePeriod1 = WavePeriod()
        wavePeriod1.sg = "2.2"
        val windDirection1 = WindDirection()
        windDirection1.sg = "2.2"
        val windSpeed1 = WindSpeed()
        windSpeed1.sg = "2.2"
        val hour1 = Hours()
        hour1.airTemperature = airTemp1
        hour1.waveHeight = waveHeight1
        hour1.wavePeriod = wavePeriod1
        hour1.windDirection = windDirection1
        hour1.windSpeed = windSpeed1


        val airTemp2 = AirTemperature()
        airTemp2.sg = "2.2"
        val waveHeight2 = WaveHeight()
        waveHeight2.sg = "2.2"
        val wavePeriod2 = WavePeriod()
        wavePeriod2.sg = "2.2"
        val windDirection2 = WindDirection()
        windDirection2.sg = "2.2"
        val windSpeed2 = WindSpeed()
        windSpeed2.sg = "2.2"
        val hour2 = Hours()
        hour2.airTemperature = airTemp2
        hour2.waveHeight = waveHeight2
        hour2.wavePeriod = wavePeriod2
        hour2.windDirection = windDirection2
        hour2.windSpeed = windSpeed2

        mockedHours.add(hour1)
        mockedHours.add(hour2)
        mockedResponse.hours = mockedHours
        mockedResponse.locationName = name
        return mockedResponse
    }

}