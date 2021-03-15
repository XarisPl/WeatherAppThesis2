package com.example.weatherappthesis.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherappthesis.ApiResponse
import com.example.weatherappthesis.Constants.Companion.LOCATION_1_LAT
import com.example.weatherappthesis.Constants.Companion.LOCATION_1_LON
import com.example.weatherappthesis.Constants.Companion.LOCATION_2_LAT
import com.example.weatherappthesis.Constants.Companion.LOCATION_2_LON
import com.example.weatherappthesis.Constants.Companion.LOCATION_3_LAT
import com.example.weatherappthesis.Constants.Companion.LOCATION_3_LON
import com.example.weatherappthesis.Constants.Companion.LOCATION_4_LAT
import com.example.weatherappthesis.Constants.Companion.LOCATION_4_LON
import com.example.weatherappthesis.Constants.Companion.LOCATION_5_LAT
import com.example.weatherappthesis.Constants.Companion.LOCATION_5_LON
import com.example.weatherappthesis.Constants.Companion.LOCATION_6_LAT
import com.example.weatherappthesis.Constants.Companion.LOCATION_6_LON
import com.example.weatherappthesis.R
import com.example.weatherappthesis.databinding.FragmentLocationsBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LocationsFragment : DaggerFragment(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentInjector

    private val locationsViewModel: LocationsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LocationsViewModel::class.java)
    }

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentLocationsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {

    }

    private fun initObservers() {
        locationsViewModel.stormGlassResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is ApiResponse.Success -> {
                    val bundle = bundleOf("response" to response.data)
                    setSpinnerVisibility(false)
                    findNavController().navigate(R.id.action_locations_to_details, bundle)
                }
            }
        })
    }

    private fun initListeners() {
        binding.btLocation1.setOnClickListener {
            setSpinnerVisibility(true)
            locationsViewModel.fetchWeather(
                LOCATION_1_LAT,
                LOCATION_1_LON,
                getString(R.string.location1)
            )
        }
        binding.btLocation2.setOnClickListener {
            setSpinnerVisibility(true)
            locationsViewModel.fetchWeather(
                LOCATION_2_LAT,
                LOCATION_2_LON,
                getString(R.string.location2)
            )
        }
        binding.btLocation3.setOnClickListener {
            setSpinnerVisibility(true)
            locationsViewModel.fetchWeather(
                LOCATION_3_LAT,
                LOCATION_3_LON,
                getString(R.string.location3)
            )
        }
        binding.btLocation4.setOnClickListener {
            setSpinnerVisibility(true)
            locationsViewModel.fetchWeather(
                LOCATION_4_LAT,
                LOCATION_4_LON,
                getString(R.string.location4)
            )
        }
        binding.btLocation5.setOnClickListener {
            setSpinnerVisibility(true)
            locationsViewModel.fetchWeather(
                LOCATION_5_LAT,
                LOCATION_5_LON,
                getString(R.string.location5)
            )
        }
        binding.btLocation6.setOnClickListener {
            setSpinnerVisibility(true)
            locationsViewModel.fetchWeather(
                LOCATION_6_LAT,
                LOCATION_6_LON,
                getString(R.string.location6)
            )
        }
    }

    private fun setSpinnerVisibility(isVisible: Boolean) {
        binding.pbLocations.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}