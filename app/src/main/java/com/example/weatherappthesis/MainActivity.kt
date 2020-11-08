package com.example.weatherappthesis

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weatherappthesis.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import com.example.weatherappthesis.Constants.Companion.WEATHER_HUMIDITY
import com.example.weatherappthesis.Constants.Companion.WEATHER_PRESSURE
import com.example.weatherappthesis.Constants.Companion.WEATHER_SUNRISE
import com.example.weatherappthesis.Constants.Companion.WEATHER_SUNSET
import com.example.weatherappthesis.Constants.Companion.WEATHER_VISIBILITY
import com.example.weatherappthesis.Constants.Companion.WEATHER_WIND


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var locationManager: LocationManager? = null

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
//            mainViewModel.fetchWeatherByLocation(location?.latitude.toString(), location?.longitude.toString())
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            // No implementation needed
        }

        override fun onProviderEnabled(provider: String?) {
            // No implementation needed
        }

        override fun onProviderDisabled(provider: String?) {
            // No implementation needed
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        askForLocationPermissions()
        initView()
        initListeners()
        initObservables()
    }

    private fun askForLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
        } else {
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0F,
                locationListener
            )
        }
    }

    private fun initView() {
        mainViewModel = MainViewModel()
        mainViewModel.fetchWeatherByCityName("arta")
    }

    private fun initListeners() {
        bt_3hrs_forcast.setOnClickListener {
            val tempData = mainViewModel.fetchData()
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(WEATHER_SUNRISE, tempData?.sys?.sunrise)
                putExtra(WEATHER_SUNSET, tempData?.sys?.sunset)
                putExtra(WEATHER_WIND, tempData?.wind?.speed.toString() + tempData?.wind?.deg.toString())
                putExtra(WEATHER_HUMIDITY, tempData?.main?.humidity.toString())
                putExtra(WEATHER_PRESSURE, tempData?.main?.pressure.toString())
                putExtra(WEATHER_VISIBILITY, tempData?.visibility.toString())
            }
            if (tempData != null) startActivity(intent)
        }
    }

    private fun initObservables() {
        mainViewModel.weatherResponse.observe(this, Observer { response ->
            when (response) {
                is ApiResponse.Success -> {
                    applyInformationToScreen(response.data)
                    mainViewModel.cacheData(response.data)
                }
            }
        })
    }

    private fun applyInformationToScreen(data: WeatherResponse?) {
        tv_location.text = data?.location.toString()
        tv_description.text = data?.weather?.get(0)?.description
        tv_temperature.text = data?.main?.temp?.toInt().toString() + " \u2103"

        val sdf = SimpleDateFormat("EEEE")
        val dateFormat: Date? = data?.sys?.sunrise?.toLong()?.times(1000)?.let { Date(it) }
        val weekday: String = sdf.format(dateFormat)
        tv_curruent_day.text = weekday

        tv_tempmin.text = "Min: " + data?.main?.temp_min?.toInt().toString() + " \u2103"
        tv_tempmax.text = "Max: " + data?.main?.temp_max?.toInt().toString() + " \u2103"

        Glide.with(this)
            .load("https://openweathermap.org/img/wn/" + data?.weather?.get(0)?.icon + ".png")
            .into(iv_icon)
    }

}
