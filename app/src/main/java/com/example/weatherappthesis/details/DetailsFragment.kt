package com.example.weatherappthesis.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weatherappthesis.R
import com.example.weatherappthesis.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel

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
        detailsViewModel = DetailsViewModel()
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