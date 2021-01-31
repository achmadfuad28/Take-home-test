package com.android.bareksatest.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import com.android.bareksatest.R
import com.android.bareksatest.databinding.ActivityMainBinding
import com.android.bareksatest.presentation.base.BaseActivity
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.owner.ViewModelOwner
import com.android.bareksatest.presentation.fragment.FundsFragment
import com.android.bareksatest.presentation.fragment.YieldFragment
import com.android.bareksatest.presentation.utils.ViewPagerAdapterUtils
import com.android.bareksatest.presentation.view.MainView
import com.android.bareksatest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(),
        MainView,
        ViewModelOwner<MainViewModel>,
        ViewDataBindingOwner<ActivityMainBinding> {
    override lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.let {
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = "Perbandingan"
        }

        initAllTabs()
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main
    }

    private fun initAllTabs() {
        val adapter = ViewPagerAdapterUtils(supportFragmentManager)
        adapter.addFragment(YieldFragment(), "Imbal Hasil")
        adapter.addFragment(FundsFragment(), "Dana Kelolaan")
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.setSelectedTabIndicatorColor(getColor(R.color.primary_800))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.itemId.let {
            when (it) {
                android.R.id.home -> onBackPressed()
            }
            return true
        }
    }

}