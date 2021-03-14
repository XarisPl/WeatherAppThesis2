package com.example.weatherappthesis

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.data.StormGlassRepository
import com.example.weatherappthesis.model.WeatherResponse
import javax.inject.Inject

class MainViewModel @Inject constructor(private val stormGlassRepository: StormGlassRepository) : ViewModel() {

    private var cachedData: WeatherResponse? = null

    fun cacheData(data: WeatherResponse?) {
        cachedData = data
    }

    fun setTemp(number: Int) {
        stormGlassRepository.setTemp(number)
    }

}