package com.example.weatherappthesis.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherappthesis.R
import com.example.weatherappthesis.databinding.FragmentDetailsBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
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
    }

    private fun initViews() {
        binding.tvLocationName.text = detailsViewModel.getLocationName()
    }

    private fun initListeners() {
        binding.btForecast24.setOnClickListener {
            findNavController().navigate(R.id.action_details_to_forecast)
        }
    }
}