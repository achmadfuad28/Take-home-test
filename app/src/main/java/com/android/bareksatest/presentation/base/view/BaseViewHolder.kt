package com.android.bareksatest.presentation.base.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.owner.ViewModelOwner
import com.android.bareksatest.BR

abstract class BaseViewHolder<T>(val context: Context, view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    init {
        if (this is ViewDataBindingOwner<*>) {
            setViewBinding(view)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, viewModel)
                binding.executePendingBindings();
            }
            if (this is BindingViewHolder<*>) {
                binding.setVariable(BR.view, this)
            }
        }
    }

    abstract fun bindData(data: T)
}
