package com.song2.thenaun.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseActivity
import com.song2.thenaun.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_main

    private val playViewModel: PlayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBottomNavigation()
        binding.playViewModel = playViewModel
    }

    override fun initObserver() {}

    private fun setBottomNavigation() {
        val navController = supportFragmentManager.findFragmentById(R.id.navigation_fragment)
        val bottomNav = binding.bottomNavi

        bottomNav.setupWithNavController(navController!!.findNavController())
    }
}