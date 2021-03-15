package com.example.weatherappthesis.details

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.model.Difficulty
import com.example.weatherappthesis.model.StormGlassResponse
import com.example.weatherappthesis.util.*
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {

    private var data: StormGlassResponse? = null

    fun setData(response: StormGlassResponse?) {
        data = response
    }

    fun getLocationName() = data?.locationName

    fun getAirTemperatureString() = data?.hoursList?.get(0)?.airTemperature?.let { getTemp(it) }

    fun getWaveHeightString() = data?.hoursList?.get(0)?.waveHeight?.let { getHeight(it) }

    fun getWavePeriodString() = data?.hoursList?.get(0)?.wavePeriod?.let { getSeconds(it) }

    fun getWindSpeedString() = data?.hoursList?.get(0)?.windSpeed?.let { getBF(it) }

    fun calculateDifficulty() = data?.let { calculate(it) }
}