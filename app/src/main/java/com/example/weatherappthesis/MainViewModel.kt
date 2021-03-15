package com.example.weatherappthesis

import androidx.lifecycle.ViewModel
import com.example.weatherappthesis.data.StormGlassRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val stormGlassRepository: StormGlassRepository) :
    ViewModel()