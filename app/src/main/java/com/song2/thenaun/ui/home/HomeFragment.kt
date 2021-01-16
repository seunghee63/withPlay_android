package com.song2.thenaun.ui.home

import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_home

    override fun initObserver() {}
}