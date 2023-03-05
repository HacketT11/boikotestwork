package com.example.boikotest

import android.app.Application
import com.example.boikotest.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BoikoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BoikoApp)
            modules(mainModule)
        }
    }
}