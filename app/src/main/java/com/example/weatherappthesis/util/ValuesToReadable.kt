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