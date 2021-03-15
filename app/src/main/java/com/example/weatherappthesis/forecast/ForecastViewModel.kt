package com.example.weatherappthesis.forecast

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.data.StormGlassRepository
import com.example.weatherappthesis.model.StormGlassResponse
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val stormGlassRepository: StormGlassRepository) :
    ViewModel() {

    private var data: StormGlassResponse? = null

    fun setData(response: StormGlassResponse?) {
        data = response
    }

    fun getData() = data

}