package com.android.bareksatest.presentation.di.component

import com.android.bareksatest.presentation.di.module.baseAppModule
import org.koin.core.module.Module

val baseAppComponent: List<Module> = listOf(
        baseAppModule
)