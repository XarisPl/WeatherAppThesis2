package com.example.weatherappthesis.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappthesis.MainViewModel
import com.example.weatherappthesis.dagger.annotations.ViewModelKey
import com.example.weatherappthesis.details.DetailsViewModel
import com.example.weatherappthesis.forecast.ForecastViewModel
import com.example.weatherappthesis.locations.LocationsViewModel
import com.example.weatherappthesis.welcome.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModels(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    internal abstract fun bindWelcomeViewModel(welcomeViewModel: WelcomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    internal abstract fun bindLocationsViewModel(locationsViewModel: LocationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    internal abstract fun bindForecastViewModel(forecastViewModel: ForecastViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}