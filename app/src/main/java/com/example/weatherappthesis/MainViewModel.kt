package com.example.weatherappthesis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _weatherResponse = MediatorLiveData<ApiResponse<WeatherResponse>>()
    val weatherResponse: LiveData<ApiResponse<WeatherResponse>> = _weatherResponse

    private val manager = OpenWeatherApiManager()
    private val api = manager.getService()
    private val repository: WeatherRepository =
        WeatherRepository(api)

    fun fetchWeatherByLocation(lat: String, lon: String) {
        _weatherResponse.addSource(
            repository.getWeatherByLocation(lat, lon)
        ) {
            _weatherResponse.value = it
        }

    }

    fun fetchWeatherByCityName(cityName: String) {
        _weatherResponse.addSource(
            repository.getWeatherByCityName(cityName)
        ) {
            _weatherResponse.value = it
        }

    }
}