package com.song2.thenaun.di

import com.song2.thenaun.ui.PlayViewModel
import com.song2.thenaun.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { HomeViewModel() }
    viewModel { PlayViewModel() }
}
