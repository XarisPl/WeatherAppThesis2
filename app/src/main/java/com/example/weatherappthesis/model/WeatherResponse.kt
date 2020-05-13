package com.example.weatherappthesis.model

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("sys")
    var sys: Sys? = null
    @SerializedName("main")
    var main: Main? = null
    @SerializedName("wind")
    var wind: Wind? = null
    @SerializedName("weather")
    var weather: Weather? = null
    @SerializedName("name")
    var location: String? = null
    @SerializedName("visibility")
    var visibility: Int? = null
}

class Main {
    @SerializedName("temp")
    var temp: Float = 0.0f
    @SerializedName("humidity")
    var humidity: Float = 0.0f
    @SerializedName("pressure")
    var pressure: Float = 0.0f
    @SerializedName("temp_min")
    var temp_min: Float = 0.0f
    @SerializedName("temp_max")
    var temp_max: Float = 0.0f
}

class Sys {
    @SerializedName("country")
    var country: String? = null
    @SerializedName("sunrise")
    var sunrise: String? = null
    @SerializedName("sunset")
    var sunset: String? = null
}

class Wind {
        @SerializedName("speed")
        var speed: Float = 0.0f
        @SerializedName("direction")
        var deg: Float = 0.0f
}

class Weather {
    @SerializedName("description")
    var description: String? = null
}



