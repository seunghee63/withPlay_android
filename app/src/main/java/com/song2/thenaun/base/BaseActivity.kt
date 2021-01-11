package com.song2.thenaun.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    abstract val layoutResId: Int
    abstract val viewModel: VM
    lateinit var binding: VB


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this

        initObserver()
    }

    abstract fun initObserver()
}