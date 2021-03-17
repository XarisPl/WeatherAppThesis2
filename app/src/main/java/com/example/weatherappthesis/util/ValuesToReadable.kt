package com.example.weatherappthesis.util

import com.example.weatherappthesis.Constants.Companion.EMPTY

fun getTemp(temp: Double): String {
    val string = temp.toString()
    return "Air temperature: $string\u2103"
}

fun getHeight(temp: Double): String {
    val string = temp.toString()
    return "Wave height: $string" + "m"
}

fun getSeconds(temp: Double): String {
    val string = temp.toString()
    return "Wave period: $string" + "sec"
}

fun getBF(windSpeed: Double, windDirection: Double? = null): String {
    val string = windSpeed.toString()
    return when (windDirection) {
        null -> "Wind speed: $string" + "BF"
        else -> "Wind: $string" + "BF " + getWindDirection(windDirection)
    }
}

fun getDegrees(temp: Double): String {
    val string = temp.toString()
    return "Wind direction: $string\u00B0" + getWindDirection(temp)
}

fun getHour(temp: String): String {
    val result = temp.subSequence(11, 16)
    return result.toString()
}

private fun getWindDirection(degrees: Double): String {
    return when {
        (degrees in 348.75..360.0) || (degrees in 0.0..11.25) -> "N"
        (degrees in 11.25..33.75) -> "NNE"
        (degrees in 33.75..56.25) -> "NE"
        (degrees in 56.25..78.75) -> "ENE"
        (degrees in 78.75..101.25) -> "E"
        (degrees in 101.25..123.75) -> "ESE"
        (degrees in 123.75..146.25) -> "SE"
        (degrees in 146.25..168.75) -> "SSE"
        (degrees in 168.75..191.25) -> "S"
        (degrees in 191.25..213.75) -> "SSW"
        (degrees in 213.75..236.25) -> "SW"
        (degrees in 236.25..258.75) -> "WSW"
        (degrees in 258.75..281.25) -> "W"
        (degrees in 281.25..303.75) -> "WNW"
        (degrees in 303.75..326.25) -> "NW"
        (degrees in 326.25..348.75) -> "NNW"
        else -> EMPTY
    }
}