package com.example.weatherappthesis.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherappthesis.R
import com.example.weatherappthesis.databinding.FragmentWelcomeBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WelcomeFragment : DaggerFragment(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentInjector

    private val welcomeViewModel: WelcomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(WelcomeViewModel::class.java)
    }

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListeners()
        initView()
    }

    private fun initView() {
        binding.scMocked.isChecked = welcomeViewModel.getIsMocked()
    }

    private fun initListeners() {
        binding.btLocation.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_locations)
        }

        binding.scMocked.setOnCheckedChangeListener { _, isChecked ->
            welcomeViewModel.switchMockedResponse(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}