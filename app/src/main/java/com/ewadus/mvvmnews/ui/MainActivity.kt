package com.ewadus.mvvmnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ewadus.mvvmnews.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val hostFragment = findViewById<FragmentContainerView>(R.id.nav_host_fragment)
        btnNavView.setupWithNavController(hostFragment.findNavController())
    }
}