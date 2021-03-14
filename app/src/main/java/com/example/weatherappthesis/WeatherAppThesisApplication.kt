package com.example.weatherappthesis

import com.example.weatherappthesis.dagger.DaggerAppComponent
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector

@Suppress("unused")
class WeatherAppThesisApplication : DaggerApplication(), HasAndroidInjector {

    private val appComponent = DaggerAppComponent.factory().create(this)

    override fun applicationInjector() = appComponent
}