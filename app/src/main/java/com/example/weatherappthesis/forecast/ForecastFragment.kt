package com.example.weatherappthesis.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherappthesis.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {

    private lateinit var forecastViewModel: ForecastViewModel

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        forecastViewModel = ForecastViewModel()
        initViews()
    }

    private fun initViews() {

    }

}