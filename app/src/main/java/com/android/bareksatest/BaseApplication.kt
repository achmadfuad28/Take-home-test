package com.android.bareksatest

import android.annotation.SuppressLint
import androidx.multidex.MultiDexApplication
import com.android.bareksatest.presentation.di.component.baseAppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class BaseApplication : MultiDexApplication() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            modules(baseAppComponent)
        }
    }
}
