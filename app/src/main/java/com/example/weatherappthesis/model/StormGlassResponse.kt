package com.example.weatherappthesis.model

import com.google.gson.annotations.SerializedName

class StormGlassResponse {
    @SerializedName("hours")
    var hours: Collection<Hours>? = null
}

class Hours {
    @SerializedName("time")
    var time: String? = null
    @SerializedName("airTemperature")
    var airTemperature: AirTemperature? = null
    @SerializedName("waveHeight")
    var waveHeight: WaveHeight? = null
    @SerializedName("wavePeriod")
    var wavePeriod: WavePeriod? = null
    @SerializedName("windDirection")
    var windDirection: WindDirection? = null
    @SerializedName("windSpeed")
    var windSpeed: WindSpeed? = null
}

class AirTemperature {
    @SerializedName("sg")
    var sg: String? = null
}

class WaveHeight {
    @SerializedName("sg")
    var sg: String? = null
}

class WavePeriod {
    @SerializedName("sg")
    var sg: String? = null
}

class WindDirection {
    @SerializedName("sg")
    var sg: String? = null
}

class WindSpeed {
    @SerializedName("sg")
    var sg: String? = null
}