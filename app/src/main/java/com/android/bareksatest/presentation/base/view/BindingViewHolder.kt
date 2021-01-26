package com.android.bareksatest.presentation.base.view

import android.view.View

interface BindingViewHolder<in T> {

    fun onItemClick(view: View, item: T)
}
