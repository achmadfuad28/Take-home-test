package com.android.bareksatest.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bareksatest.R
import com.android.bareksatest.data.entities.CompareCategoryList
import com.android.bareksatest.databinding.FragmentYieldBinding
import com.android.bareksatest.presentation.adapter.CategoryListAdapter
import com.android.bareksatest.presentation.base.BaseFragment
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.owner.ViewModelOwner
import com.android.bareksatest.presentation.utils.CommonUtils
import com.android.bareksatest.presentation.view.YieldView
import com.android.bareksatest.presentation.viewmodel.YieldViewModel
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


open class YieldFragment : BaseFragment(),
        YieldView,
        ViewModelOwner<YieldViewModel>,
        ViewDataBindingOwner<FragmentYieldBinding> {

    override lateinit var binding: FragmentYieldBinding
    override val viewModel: YieldViewModel by viewModel()
    override var compareCategoryAdapter = CategoryListAdapter()
    override lateinit var compareCategoryLayoutManager: LinearLayoutManager

    override fun getViewLayoutResId(): Int {
        return R.layout.fragment_yield
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compareCategoryLayoutManager = LinearLayoutManager(requireContext())
        getCompareDataFromMock()
        setUpGraph()
        binding.tabLayout.setSelectedTabIndicatorColor(requireActivity().getColor(R.color.primary_800))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("1W"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("1M"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("1Y"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("3Y"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("5Y"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("10Y"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All"))

    }

    private fun getCompareDataFromMock() {
        val mockFile = "Data.json"

        val compareCategoryList =
                CommonUtils.convertStreamToJsonClass(requireContext(), mockFile, CompareCategoryList::class.java)

        compareCategoryList?.let { list ->
            list.data?.let {
                compareCategoryAdapter.setData(it)
                compareCategoryAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setUpGraph () {
        val date = "05/2019"
        val dateFormat = SimpleDateFormat("MM/yyyy")
        val convertedDate = dateFormat.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = convertedDate
        val d1 = calendar.time
        calendar.add(Calendar.MONTH, 4)
        val d2 = calendar.time
        calendar.add(Calendar.MONTH, 4)
        val d3 = calendar.time
        calendar.add(Calendar.MONTH, 4)
        val d4 = calendar.time
        calendar.add(Calendar.MONTH, 4)
        val d5 = calendar.time

        val series1 = LineGraphSeries(
                arrayOf(
                        DataPoint(d1, 0.0),
                        DataPoint(d2, 5.0),
                        DataPoint(d3, 10.0),
                        DataPoint(d4, 20.0),
                        DataPoint(d5, 40.0)
                )
        ).apply {
            color = R.color.navy
        }
        val series2 = LineGraphSeries(
                arrayOf(
                        DataPoint(d1, 0.0),
                        DataPoint(d2, 3.0),
                        DataPoint(d3, 8.5),
                        DataPoint(d4, 15.5),
                        DataPoint(d5, 28.0)
                )
        ).apply {
            color = R.color.violet
        }

        val series3 = LineGraphSeries(
                arrayOf(
                        DataPoint(d1, 0.0),
                        DataPoint(d2, 1.0),
                        DataPoint(d3, 2.5),
                        DataPoint(d4, 4.5),
                        DataPoint(d5, 7.0)
                )
        ).apply {
            color = R.color.green
        }
        binding.graph.addSeries(series1)
        binding.graph.addSeries(series2)
        binding.graph.addSeries(series3)

        binding.graph.gridLabelRenderer.apply {
            labelFormatter = DateAsXAxisLabelFormatter(activity, SimpleDateFormat("MMM yy", Locale.US))
            textSize = 25F
        }
    }

}