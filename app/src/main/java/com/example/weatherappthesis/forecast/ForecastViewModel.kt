package com.example.weatherappthesis.forecast

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.data.StormGlassRepository
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val stormGlassRepository: StormGlassRepository) : ViewModel() {

    fun getTemp() = stormGlassRepository.getTemp()

}