package com.example.weatherappthesis.util

import com.example.weatherappthesis.R
import com.example.weatherappthesis.model.Difficulty
import com.example.weatherappthesis.model.DifficultyLevel
import com.example.weatherappthesis.model.StormGlassResponse

fun calculate(data: StormGlassResponse): Difficulty {
    val result = Difficulty()

    val waveHeightLevel = data.hoursList[0].waveHeight?.let { checkWaveHeight(it) }
    val wavePeriodLevel = data.hoursList[0].wavePeriod?.let { checkWavePeriod(it) }
    val windSpeedLevel = data.hoursList[0].windSpeed?.let { checkWindSpeed(it) }

    var maximumLevel = 0
    if (waveHeightLevel != null && waveHeightLevel > maximumLevel) maximumLevel = waveHeightLevel
    if (wavePeriodLevel != null && wavePeriodLevel > maximumLevel) maximumLevel = wavePeriodLevel
    if (windSpeedLevel != null && windSpeedLevel > maximumLevel) maximumLevel = windSpeedLevel

    when (maximumLevel) {
        1 -> {
            result.level = DifficultyLevel.EASY
            result.colourId = R.color.colorEasy
            result.messageId = R.string.messageEasy
        }
        2 -> {
            result.level = DifficultyLevel.MODERATE
            result.colourId = R.color.colorModerate
            result.messageId = R.string.messageModerate
        }
        3 -> {
            result.level = DifficultyLevel.ADVANCED
            result.colourId = R.color.colorAdvanced
            result.messageId = R.string.messageAdvanced
        }
        4 -> {
            result.level = DifficultyLevel.EXTREME
            result.colourId = R.color.colorExtreme
            result.messageId = R.string.messageExtreme
        }
    }

    return result
}

private fun checkWaveHeight(data: Double): Int {
    return when {
        data <= 0.5 -> 1
        data > 0.5 && data <= 1 -> 2
        data > 1 && data <= 1.7 -> 3
        data > 1.7 -> 4
        else -> 0
    }
}

private fun checkWavePeriod(data: Double): Int {
    return when {
        data <= 8 -> 1
        data > 8 && data <= 10 -> 2
        data > 10 -> 4
        else -> 0
    }
}

private fun checkWindSpeed(data: Double): Int {
    return when {
        data <= 3 -> 1
        data > 3 && data <= 5 -> 2
        data > 5 && data <= 7 -> 3
        data > 7 -> 4
        else -> 0
    }
}