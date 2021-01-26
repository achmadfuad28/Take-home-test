package com.android.bareksatest.presentation.di.module

import android.app.Application
import android.content.SharedPreferences
import com.android.bareksatest.presentation.base.module.AppModule
import com.android.bareksatest.presentation.viewmodel.CategoryListViewModel
import com.android.bareksatest.presentation.viewmodel.YieldViewModel
import com.android.bareksatest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class BaseAppModule(app: Application) : AppModule(app)

val baseAppModule = module {
    factory { (app: Application) -> BaseAppModule(app) }
    single<SharedPreferences> { get()}

    viewModel {
        MainViewModel()
    }

    viewModel {
        YieldViewModel()
    }
    viewModel {
        CategoryListViewModel()
    }
}