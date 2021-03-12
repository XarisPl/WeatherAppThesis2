package com.example.weatherappthesis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.data.StormGlassRepository
import com.example.weatherappthesis.model.StormGlassResponse
import com.example.weatherappthesis.model.WeatherResponse
import com.example.weatherappthesis.network.StormGlassApiManager

class MainViewModel : ViewModel() {

    private var cachedData: WeatherResponse? = null
    private val _weatherResponse = MediatorLiveData<ApiResponse<WeatherResponse>>()
    val weatherResponse: LiveData<ApiResponse<WeatherResponse>> = _weatherResponse

    private val _stormGlassResponse = MediatorLiveData<ApiResponse<StormGlassResponse>>()
    val stormGlassResponse: LiveData<ApiResponse<StormGlassResponse>> = _stormGlassResponse

    private val openWeatherApiManager = OpenWeatherApiManager()
    private val stormGlassApiManager = StormGlassApiManager()
    private val openWeatherApi = openWeatherApiManager.getService()
    private val stormGlassApi = stormGlassApiManager.getService()
    private val weatherRepository: WeatherRepository =
        WeatherRepository(openWeatherApi)
    private val stormGlassRepository: StormGlassRepository =
        StormGlassRepository(stormGlassApi)

    fun fetchWeather(lat: String, lon: String) {
        _stormGlassResponse.addSource(
            stormGlassRepository.getWeather(lat, lon)
        ) {
            _stormGlassResponse.value = it
        }
    }

    fun fetchWeatherByLocation(lat: String, lon: String) {
        _weatherResponse.addSource(
            weatherRepository.getWeatherByLocation(lat, lon)
        ) {
            _weatherResponse.value = it
        }

    }

    fun fetchWeatherByCityName(cityName: String) {
        _weatherResponse.addSource(
            weatherRepository.getWeatherByCityName(cityName)
        ) {
            _weatherResponse.value = it
        }

    }

    fun cacheData(data: WeatherResponse?) {
        cachedData = data
    }

    fun fetchData() = cachedData
}