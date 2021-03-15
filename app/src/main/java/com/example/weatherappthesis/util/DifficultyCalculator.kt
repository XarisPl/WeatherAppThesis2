package com.example.weatherappthesis.util

import com.example.weatherappthesis.R
import com.example.weatherappthesis.model.Difficulty
import com.example.weatherappthesis.model.DifficultyLevel
import com.example.weatherappthesis.model.Hour
import com.example.weatherappthesis.model.StormGlassResponse

fun calculate(data: StormGlassResponse): Difficulty {
    val result = Difficulty()

    when {
        isEasy(data.hoursList[0]) -> {
            result.level = DifficultyLevel.EASY
            result.colourId = R.color.colorEasy
            result.messageId = R.string.messageEasy
        }
        isModerate(data.hoursList[0]) -> {
            result.level = DifficultyLevel.MODERATE
            result.colourId = R.color.colorModerate
            result.messageId = R.string.messageModerate
        }
        isAdvanced(data.hoursList[0]) -> {
            result.level = DifficultyLevel.ADVANCED
            result.colourId = R.color.colorAdvanced
            result.messageId = R.string.messageAdvanced
        }
        isExtreme(data.hoursList[0]) -> {
            result.level = DifficultyLevel.EXTREME
            result.colourId = R.color.colorExtreme
            result.messageId = R.string.messageExtreme
        }
    }

    return result
}

private fun isEasy(hour: Hour): Boolean {
    return hour.waveHeight!! < 0.5 &&
            hour.wavePeriod!! < 8 &&
            hour.windSpeed!! <= 3
}

private fun isModerate(hour: Hour): Boolean {
    return (hour.waveHeight!! > 0.5 && hour.waveHeight!! < 1) &&
            (hour.wavePeriod!! > 8 && hour.wavePeriod!! < 10) &&
            hour.windSpeed!! > 3 && hour.windSpeed!! <= 5
}

private fun isAdvanced(hour: Hour): Boolean {
    return (hour.waveHeight!! > 1 && hour.waveHeight!! < 1.7) &&
            (hour.wavePeriod!! > 8 && hour.wavePeriod!! < 10) &&
            hour.windSpeed!! > 5 && hour.windSpeed!! <= 7
}

private fun isExtreme(hour: Hour): Boolean {
    return hour.waveHeight!! > 1.7 &&
            hour.wavePeriod!! > 10 &&
            hour.windSpeed!! > 7
}