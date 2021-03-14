package com.example.weatherappthesis.details

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.model.StormGlassResponse
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {

    private var data: StormGlassResponse? = null

    fun setData(response: StormGlassResponse?) {
        data = response
    }

    fun getLocationName() = data?.locationName
}