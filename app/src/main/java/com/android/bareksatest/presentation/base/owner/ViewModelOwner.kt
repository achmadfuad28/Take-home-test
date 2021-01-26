package com.android.bareksatest.presentation.base.owner

import com.android.bareksatest.presentation.base.BaseViewModel

interface ViewModelOwner<T : BaseViewModel> {
    val viewModel: T
}