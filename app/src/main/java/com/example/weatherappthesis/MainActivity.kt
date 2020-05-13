package com.example.weatherappthesis

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.weatherappthesis.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var locationManager: LocationManager? = null

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            mainViewModel.fetchWeatherByLocation(location?.latitude.toString(), location?.longitude.toString())
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
                    != PackageManager.PERMISSION_GRANTED)) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
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



    }

    private fun initListeners() {

    }

    private fun initObservables() {
        mainViewModel.weatherResponse.observe(this, Observer { response ->
            when(response) {
                is ApiResponse.Success -> {
                    applyInformationToScreen(response.data)
                }
            }
        })
    }

    private fun applyInformationToScreen(data: WeatherResponse?) {
        tv_temperature.text = data?.main?.temp.toString()
        tv_humidity.text = data?.main?.humidity.toString()
        tv_pressure.text = data?.main?.pressure.toString()
        tv_tempmin.text = data?.main?.temp_min.toString()
        tv_tempmax.text = data?.main?.temp_max.toString()
        tv_sunrise.text = data?.sys?.sunrise.toString()
        tv_sunset.text = data?.sys?.sunset.toString()
        //κατι μου λεει πως δεν θα το κανουμε ετσι αυτο!!
        tv_wind.text = data?.wind?.speed.toString() + data?.wind?.deg.toString()
        tv_description.text = data?.weather?.description.toString()
        tv_location.text = data?.location.toString()
        tv_visibility.text = data?.visibility.toString()


}}
