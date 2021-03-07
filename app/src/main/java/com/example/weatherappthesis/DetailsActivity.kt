package com.example.weatherappthesis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappthesis.Constants.Companion.WEATHER_HUMIDITY
import com.example.weatherappthesis.Constants.Companion.WEATHER_PRESSURE
import com.example.weatherappthesis.Constants.Companion.WEATHER_SUNRISE
import com.example.weatherappthesis.Constants.Companion.WEATHER_SUNSET
import com.example.weatherappthesis.Constants.Companion.WEATHER_VISIBILITY
import com.example.weatherappthesis.Constants.Companion.WEATHER_WIND
import com.example.weatherappthesis.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

        binding.tvSunrise.text = sunrise?.toLong()?.times(1000)?.let { java.util.Date(it).toString() }
        binding.tvSunset.text = sunset?.toLong()?.times(1000)?.let { java.util.Date(it).toString() }

        binding.tvWind.text = wind
        binding.tvHumidity.text = humidity
        binding.tvPressure.text = pressure
        binding.tvVisibility.text = visibility
    }

    private fun initObservers() {
    }
}