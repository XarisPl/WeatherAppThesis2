package com.example.weatherappthesis.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappthesis.adapter.ForecastHourAdapter
import com.example.weatherappthesis.databinding.FragmentForecastBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ForecastFragment : DaggerFragment(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentInjector

    private val forecastViewModel: ForecastViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ForecastViewModel::class.java)
    }

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ForecastHourAdapter

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
        forecastViewModel.setData(arguments?.getParcelable("forecast"))
        initViews()
        initAdapter()
    }

    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvInfo.layoutManager = linearLayoutManager
    }

    private fun initAdapter() {
        adapter = forecastViewModel.getData()?.hoursList?.let { ForecastHourAdapter(it) }!!
        binding.rvInfo.adapter = adapter
    }

}