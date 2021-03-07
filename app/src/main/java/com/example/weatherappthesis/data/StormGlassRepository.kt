package com.example.weatherappthesis.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappthesis.ApiResponse
import com.example.weatherappthesis.BuildConfig
import com.example.weatherappthesis.Constants.Companion.EMPTY
import com.example.weatherappthesis.OpenWeatherApi
import com.example.weatherappthesis.model.StormGlassResponse
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

    fun getWeather(lat: String, lon:String): LiveData<ApiResponse<StormGlassResponse>> {
        val result = MutableLiveData<ApiResponse<StormGlassResponse>>()

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
                        result.postValue(
                            ApiResponse.success(
                                apiResponse.body()
                            )
                        )
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

}