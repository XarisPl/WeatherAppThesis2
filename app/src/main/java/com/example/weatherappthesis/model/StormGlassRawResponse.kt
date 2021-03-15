package com.example.weatherappthesis.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class StormGlassRawResponse : Parcelable {
    var locationName: String? = null
    @SerializedName("hours")
    var hours: Collection<HoursRaw>? = null
}

class HoursRaw {
    @SerializedName("time")
    var time: String? = null
    @SerializedName("airTemperature")
    var airTemperatureRaw: AirTemperatureRaw? = null
    @SerializedName("waveHeight")
    var waveHeightRaw: WaveHeightRaw? = null
    @SerializedName("wavePeriod")
    var wavePeriodRaw: WavePeriodRaw? = null
    @SerializedName("windDirection")
    var windDirectionRaw: WindDirectionRaw? = null
    @SerializedName("windSpeed")
    var windSpeedRaw: WindSpeedRaw? = null
}

class AirTemperatureRaw {
    @SerializedName("sg")
    var sg: String? = null
}

class WaveHeightRaw {
    @SerializedName("sg")
    var sg: String? = null
}

class WavePeriodRaw {
    @SerializedName("sg")
    var sg: String? = null
}

class WindDirectionRaw {
    @SerializedName("sg")
    var sg: String? = null
}

class WindSpeedRaw {
    @SerializedName("sg")
    var sg: String? = null
}