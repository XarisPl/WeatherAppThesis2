package com.example.weatherappthesis.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherappthesis.R
import com.example.weatherappthesis.databinding.FragmentDetailsBinding
import com.example.weatherappthesis.model.Difficulty
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : DaggerFragment(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentInjector

    private val detailsViewModel: DetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailsViewModel.setData(arguments?.getParcelable("response"))
        initViews()
        initListeners()
        adjustViewBasedOnDifficulty(detailsViewModel.calculateDifficulty())
    }


    private fun initViews() {
        binding.tvLocationName.text = detailsViewModel.getLocationName()
        binding.tvAirTemp.text = detailsViewModel.getAirTemperatureString()
        binding.tvWaveHeight.text = detailsViewModel.getWaveHeightString()
        binding.tvWavePeriod.text = detailsViewModel.getWavePeriodString()
        binding.tvWindSpeed.text = detailsViewModel.getWindSpeedString()
    }

    private fun initListeners() {
        binding.btForecast24.setOnClickListener {
            findNavController().navigate(R.id.action_details_to_forecast)
        }
    }

    private fun adjustViewBasedOnDifficulty(difficulty: Difficulty?) {
        if (difficulty != null) {
            difficulty.colourId?.let { cl_details.setBackgroundColor(ContextCompat.getColor(requireContext(), it)) }
            binding.tvDifficulty.text = difficulty.level?.title
            binding.tvMessage.text = difficulty.messageId?.let { getString(it) }
        }
    }

}