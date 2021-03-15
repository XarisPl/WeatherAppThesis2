package com.example.weatherappthesis.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.ApiResponse
import com.example.weatherappthesis.data.StormGlassRepository
import com.example.weatherappthesis.model.*
import javax.inject.Inject

class LocationsViewModel @Inject constructor(private val stormGlassRepository: StormGlassRepository) :
    ViewModel() {

    private val _stormGlassResponse = MediatorLiveData<ApiResponse<StormGlassResponse>>()
    val stormGlassResponse: LiveData<ApiResponse<StormGlassResponse>> = _stormGlassResponse

    fun fetchWeather(lat: String, lon: String, name: String) {
        _stormGlassResponse.addSource(
            stormGlassRepository.getWeather(lat, lon, name)
        ) {
            _stormGlassResponse.value = it
        }
    }
}