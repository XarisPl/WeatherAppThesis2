package com.example.weatherappthesis.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.ApiResponse
import com.example.weatherappthesis.data.StormGlassRepository
import com.example.weatherappthesis.model.*
import com.example.weatherappthesis.network.StormGlassApiManager

class LocationsViewModel : ViewModel() {

    private val _stormGlassResponse = MediatorLiveData<ApiResponse<StormGlassResponse>>()
    val stormGlassResponse: LiveData<ApiResponse<StormGlassResponse>> = _stormGlassResponse

    private val stormGlassApiManager = StormGlassApiManager()

    private val stormGlassApi = stormGlassApiManager.getService()

    private val stormGlassRepository: StormGlassRepository =
        StormGlassRepository(stormGlassApi)

    fun fetchWeather(lat: String, lon: String, name: String) {
        _stormGlassResponse.addSource(
            stormGlassRepository.getWeather(lat, lon, name, true)
        ) {
            _stormGlassResponse.value = it
        }
    }
}