package com.android.bareksatest.presentation.fragment

import android.os.Bundle
import android.view.View
import com.android.bareksatest.R
import com.android.bareksatest.databinding.FragmentYieldBinding
import com.android.bareksatest.presentation.base.BaseFragment
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.owner.ViewModelOwner
import com.android.bareksatest.presentation.view.YieldView
import com.android.bareksatest.presentation.viewmodel.YieldViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class FundsFragment : BaseFragment(){

    override fun getViewLayoutResId(): Int {
        return R.layout.fragment_funds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}