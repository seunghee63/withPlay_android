package com.song2.thenaun.ui.detailed

import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentDetailedBinding

class DetailedFragment : BaseFragment<FragmentDetailedBinding>(){
    override val layoutResId: Int
        get() = R.layout.fragment_detailed

    override fun initObserver() {}
}