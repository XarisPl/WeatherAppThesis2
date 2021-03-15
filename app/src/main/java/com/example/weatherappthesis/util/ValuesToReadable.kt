package com.example.weatherappthesis.util

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

fun getBF(temp: Double): String {
    val string = temp.toString()
    return "Wind speed: $string" + "BF"
}

fun getDegrees(temp: Double): String {
    val string = temp.toString()
    return "Wind direction: $string\u00B0"
}

fun getHour(temp: String): String {
    val result = temp.subSequence(11, 16)
    return result.toString()
}