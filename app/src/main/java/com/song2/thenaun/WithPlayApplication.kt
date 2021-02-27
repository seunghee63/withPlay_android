package com.song2.thenaun

import android.app.Application
import com.song2.thenaun.di.viewModuleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WithPlayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WithPlayApplication)
            modules(listOf(viewModuleModule))
        }
    }
}