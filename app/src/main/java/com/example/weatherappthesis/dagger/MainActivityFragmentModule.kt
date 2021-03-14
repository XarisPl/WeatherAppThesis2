package com.example.weatherappthesis.dagger

import com.example.weatherappthesis.dagger.annotations.FragmentScope
import com.example.weatherappthesis.details.DetailsFragment
import com.example.weatherappthesis.forecast.ForecastFragment
import com.example.weatherappthesis.location.LocationsFragment
import com.example.weatherappthesis.welcome.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeWelcomeFragment(): WelcomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLocationsFragment(): LocationsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeForecastFragment(): ForecastFragment
}