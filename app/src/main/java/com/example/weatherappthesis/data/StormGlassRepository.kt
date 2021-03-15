package com.example.weatherappthesis.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappthesis.ApiResponse
import com.example.weatherappthesis.BuildConfig
import com.example.weatherappthesis.model.*
import com.example.weatherappthesis.network.StormGlassApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StormGlassRepository @Inject constructor(private val api: StormGlassApi) {

    companion object {
        private const val HTTP_STATUS_OK = 200
        private const val HTTP_STATUS_CREATED = 201
        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val PARAM_LIST =
            "airTemperature,waveHeight,wavePeriod,windDirection,windSpeed"
        private const val SG_SOURCE_KEY = "sg"
    }

    private var isMocked = false

    fun switchMockedResponse(isMocked: Boolean) {
        this.isMocked = isMocked
    }

    fun getMockedStatus() = isMocked

    fun getWeather(
        lat: String,
        lon: String,
        name: String
    ): LiveData<ApiResponse<StormGlassResponse>> {
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
                val apiResponse = api.getWeather(
                    BuildConfig.STORM_GLASS_API_KEY,
                    lat,
                    lon,
                    listOf(PARAM_LIST),
                    startDate,
                    endDate,
                    SG_SOURCE_KEY
                ).awaitResponse()
                when (apiResponse.code()) {
                    HTTP_STATUS_OK, HTTP_STATUS_CREATED -> {
                        val body = apiResponse.body()
                        body?.locationName = name
                        val usableResponse = StormGlassResponse(body)
                        result.postValue(ApiResponse.success(usableResponse))
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
        val mockedResponse = StormGlassRawResponse()
        val mockedHours = arrayListOf<HoursRaw>()
        val airTemp1 = AirTemperatureRaw()
        airTemp1.sg = "2.2"
        val waveHeight1 = WaveHeightRaw()
        waveHeight1.sg = "0.7"
        val wavePeriod1 = WavePeriodRaw()
        wavePeriod1.sg = "9.5"
        val windDirection1 = WindDirectionRaw()
        windDirection1.sg = "2.2"
        val windSpeed1 = WindSpeedRaw()
        windSpeed1.sg = "4.1"
        val hour1 = HoursRaw()
        hour1.time = "2021-03-07T12:00:00+00:00"
        hour1.airTemperatureRaw = airTemp1
        hour1.waveHeightRaw = waveHeight1
        hour1.wavePeriodRaw = wavePeriod1
        hour1.windDirectionRaw = windDirection1
        hour1.windSpeedRaw = windSpeed1


        val airTemp2 = AirTemperatureRaw()
        airTemp2.sg = "2.2"
        val waveHeight2 = WaveHeightRaw()
        waveHeight2.sg = "2.2"
        val wavePeriod2 = WavePeriodRaw()
        wavePeriod2.sg = "12.4"
        val windDirection2 = WindDirectionRaw()
        windDirection2.sg = "2.2"
        val windSpeed2 = WindSpeedRaw()
        windSpeed2.sg = "7.3"
        val hour2 = HoursRaw()
        hour2.time = "2021-03-07T13:00:00+00:00"
        hour2.airTemperatureRaw = airTemp2
        hour2.waveHeightRaw = waveHeight2
        hour2.wavePeriodRaw = wavePeriod2
        hour2.windDirectionRaw = windDirection2
        hour2.windSpeedRaw = windSpeed2

        val airTemp3 = AirTemperatureRaw()
        airTemp3.sg = "4.2"
        val waveHeight3 = WaveHeightRaw()
        waveHeight3.sg = "3.2"
        val wavePeriod3 = WavePeriodRaw()
        wavePeriod3.sg = "15.4"
        val windDirection3 = WindDirectionRaw()
        windDirection3.sg = "1.2"
        val windSpeed3 = WindSpeedRaw()
        windSpeed3.sg = "8.3"
        val hour3 = HoursRaw()
        hour3.time = "2021-03-07T14:00:00+00:00"
        hour3.airTemperatureRaw = airTemp2
        hour3.waveHeightRaw = waveHeight2
        hour3.wavePeriodRaw = wavePeriod2
        hour3.windDirectionRaw = windDirection2
        hour3.windSpeedRaw = windSpeed2

        val airTemp4 = AirTemperatureRaw()
        airTemp4.sg = "3.6"
        val waveHeight4 = WaveHeightRaw()
        waveHeight4.sg = "2.2"
        val wavePeriod4 = WavePeriodRaw()
        wavePeriod4.sg = "12.4"
        val windDirection4 = WindDirectionRaw()
        windDirection4.sg = "2.2"
        val windSpeed4 = WindSpeedRaw()
        windSpeed4.sg = "7.3"
        val hour4 = HoursRaw()
        hour4.time = "2021-03-07T15:00:00+00:00"
        hour4.airTemperatureRaw = airTemp2
        hour4.waveHeightRaw = waveHeight2
        hour4.wavePeriodRaw = wavePeriod2
        hour4.windDirectionRaw = windDirection2
        hour4.windSpeedRaw = windSpeed2

        val airTemp5 = AirTemperatureRaw()
        airTemp5.sg = "2.2"
        val waveHeight5 = WaveHeightRaw()
        waveHeight5.sg = "2.2"
        val wavePeriod5 = WavePeriodRaw()
        wavePeriod5.sg = "12.4"
        val windDirection5 = WindDirectionRaw()
        windDirection5.sg = "2.2"
        val windSpeed5 = WindSpeedRaw()
        windSpeed5.sg = "7.3"
        val hour5 = HoursRaw()
        hour5.time = "2021-03-07T16:00:00+00:00"
        hour5.airTemperatureRaw = airTemp2
        hour5.waveHeightRaw = waveHeight2
        hour5.wavePeriodRaw = wavePeriod2
        hour5.windDirectionRaw = windDirection2
        hour5.windSpeedRaw = windSpeed2

        mockedHours.add(hour1)
        mockedHours.add(hour2)
        mockedHours.add(hour3)
        mockedHours.add(hour4)
        mockedHours.add(hour5)
        mockedHours.add(hour1)
        mockedHours.add(hour2)
        mockedHours.add(hour3)
        mockedHours.add(hour4)
        mockedHours.add(hour5)
        mockedResponse.hours = mockedHours
        mockedResponse.locationName = name
        return StormGlassResponse(mockedResponse)
    }
}