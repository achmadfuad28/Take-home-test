package com.android.bareksatest.presentation.base.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import java.util.*

abstract class AppModule (protected val app : Application) {
    protected val prefs : SharedPreferences = app.getSharedPreferences("default", Context.MODE_PRIVATE)

}