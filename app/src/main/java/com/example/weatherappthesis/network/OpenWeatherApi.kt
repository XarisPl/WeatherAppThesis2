package com.example.weatherappthesis

import com.example.weatherappthesis.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather?")
    fun getWeatherInfoByLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String?
    ):
            Call<WeatherResponse>

    @GET("weather?")
    fun getWeatherInfoByCityName(
        @Query("q") cityName: String,
        @Query("appid") appid: String?,
        @Query("units") units: String
    ):
            Call<WeatherResponse>
}