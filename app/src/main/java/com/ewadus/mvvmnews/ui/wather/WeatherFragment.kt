package com.ewadus.mvvmnews.ui.wather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ewadus.mvvmnews.R
import com.ewadus.mvvmnews.databinding.FragmentWeatherBinding
import com.ewadus.mvvmnews.ui.MainViewModel
import com.ewadus.mvvmnews.util.Permissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        getWeather()

        return binding.root
    }

    @SuppressLint("MissingPermission")
    private fun getWeather() {

        if (Permissions.hasPermissionLocation(requireContext())) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                viewModel.fetchWeather(location.latitude.toString(), location.longitude.toString())
            }
        } else {
            Permissions.requestLocationPermission(this)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}