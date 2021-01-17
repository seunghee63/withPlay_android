package com.song2.thenaun.ui

import com.song2.thenaun.R
import com.song2.thenaun.base.BaseActivity
import com.song2.thenaun.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(){
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initObserver() {}
}