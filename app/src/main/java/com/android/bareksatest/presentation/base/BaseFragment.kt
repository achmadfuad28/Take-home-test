package com.android.bareksatest.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.owner.ViewModelOwner
import com.android.bareksatest.presentation.base.view.BindingView
import com.android.bareksatest.BR
import com.android.bareksatest.presentation.annotations.ViewLayout

abstract class BaseFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        check(activity is AppCompatActivity) { "Host activity must extends from ${AppCompatActivity::class.java.simpleName}" }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return getLayoutIfDefined(inflater, container)
    }

    private fun getLayoutIfDefined(inflater: LayoutInflater, container: ViewGroup?): View? {
        val layoutResId = getViewLayoutResId()
        if (layoutResId == View.NO_ID) return null

        return if (this is ViewDataBindingOwner<*>) {
            val view = inflateContentViewBinding(inflater, container, layoutResId)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, this.viewModel)
            }
            if (this is BindingView) {
                binding.setVariable(BR.view, this)
            }
            view
        } else {
            inflater.inflate(layoutResId, container, false)
        }
    }


    protected open fun getViewLayoutResId(): Int {
        val layout = javaClass.annotations.find { it is ViewLayout } as? ViewLayout
        return layout?.value ?: View.NO_ID
    }
}
