package com.example.weatherappthesis.welcome

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.data.StormGlassRepository
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(private val stormGlassRepository: StormGlassRepository) : ViewModel()  {

    fun switchMockedResponse(isMocked: Boolean) {
        stormGlassRepository.switchMockedResponse(isMocked)
    }

    fun getIsMocked() = stormGlassRepository.getMockedStatus()

}