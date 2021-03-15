package com.example.weatherappthesis.network

import com.example.weatherappthesis.model.StormGlassRawResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StormGlassApi {

    @GET("weather/point?")
    fun getWeather(
        @Header("Authorization") apiKey: String,
        @Query("lat") lat: String,
        @Query("lng") lon: String,
        @Query("params") params: List<String>,
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("source") source: String,
    ):
            Call<StormGlassRawResponse>
}