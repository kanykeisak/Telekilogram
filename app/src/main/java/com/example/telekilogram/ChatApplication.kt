package com.example.telekilogram

import android.app.Application
import com.example.telekilogram.di.appModules
import com.example.telekilogram.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChatApplication)
            modules(appModules, viewModelModule)
        }
    }
}