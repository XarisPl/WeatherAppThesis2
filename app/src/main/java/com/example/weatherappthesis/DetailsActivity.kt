package com.example.weatherappthesis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappthesis.Constants.Companion.WEATHER_HUMIDITY
import com.example.weatherappthesis.Constants.Companion.WEATHER_PRESSURE
import com.example.weatherappthesis.Constants.Companion.WEATHER_SUNRISE
import com.example.weatherappthesis.Constants.Companion.WEATHER_SUNSET
import com.example.weatherappthesis.Constants.Companion.WEATHER_VISIBILITY
import com.example.weatherappthesis.Constants.Companion.WEATHER_WIND
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val sunrise = intent.getIntExtra(WEATHER_SUNRISE, 0)
        val sunset = intent.getIntExtra(WEATHER_SUNSET, 0)
        val wind = intent.getStringExtra(WEATHER_WIND)
        val humidity = intent.getStringExtra(WEATHER_HUMIDITY)
        val pressure = intent.getStringExtra(WEATHER_PRESSURE)
        val visibility = intent.getStringExtra(WEATHER_VISIBILITY)

        initViews(sunrise, sunset, wind, humidity, pressure, visibility)
    }

    private fun initViews(
        sunrise: Int?,
        sunset: Int?,
        wind: String?,
        humidity: String?,
        pressure: String?,
        visibility: String?
    ) {

       tv_sunrise.text = sunrise?.toLong()?.times(1000)?.let { java.util.Date(it).toString() }
        tv_sunset.text = sunset?.toLong()?.times(1000)?.let { java.util.Date(it).toString() }

        //κατι μου λεει πως δεν θα το κανουμε ετσι αυτο!!
        tv_wind.text = wind
        tv_humidity.text = humidity
        tv_pressure.text = pressure
        tv_visibility.text = visibility
    }
}