package com.android.bareksatest.presentation.fragment

import android.graphics.Color
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
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
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

    private fun setUpGraph() {
        var dataSets: ArrayList<ILineDataSet?> = ArrayList()
        val xAxisValues: List<String> = ArrayList(listOf("Jan 19", "Feb 19", "Mar 19", "Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19",
                "Jan 20", "Feb 20", "Mar 20", "Apr 20", "May 20", "Jun 20", "Jul 20", "Aug 20", "Sep 20", "Oct 20", "Nov 20", "Dec 20"))
        dataSets = ArrayList()

        val incomeEntries1: List<Entry> = getIncomeEntries1()
        val set1  = LineDataSet(incomeEntries1, "Income").apply {
            color = requireActivity().getColor(R.color.green_line)
            valueTextColor = Color.rgb(55, 70, 73)
            valueTextSize = 10f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            lineWidth = 2f
            circleRadius = 3f
            setDrawValues(false)
            circleHoleColor = resources.getColor(R.color.green)
            setCircleColor(resources.getColor(R.color.green_line))
        }

        val incomeEntries2: List<Entry> = getIncomeEntries2()
        val set2  = LineDataSet(incomeEntries2, "Income").apply {
            color = requireActivity().getColor(R.color.violet_line)
            valueTextColor = Color.rgb(55, 70, 73)
            valueTextSize = 10f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            lineWidth = 2f
            circleRadius = 3f
            setDrawValues(false)
            circleHoleColor = resources.getColor(R.color.green)
            setCircleColor(resources.getColor(R.color.violet_line))
        }

        val incomeEntries3: List<Entry> = getIncomeEntries3()
        val set3  = LineDataSet(incomeEntries3, "Income").apply {
            color = requireActivity().getColor(R.color.blue_line)
            valueTextColor = Color.rgb(55, 70, 73)
            valueTextSize = 10f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            lineWidth = 2f
            circleRadius = 3f
            setDrawValues(false)
            circleHoleColor = resources.getColor(R.color.green)
            setCircleColor(resources.getColor(R.color.blue_line))
        }

        dataSets.add(set1)
        dataSets.add(set2)
        dataSets.add(set3)

        binding.graph.apply {
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(false)
            setPinchZoom(false)
            setDrawGridBackground(false)
            xAxis.setDrawGridLines(true)
            axisLeft.setDrawGridLines(true)
            axisRight.setDrawGridLines(true)
        }


        val rightYAxis = binding.graph.axisRight
        rightYAxis.isEnabled = true
        val leftYAxis = binding.graph.axisLeft
        leftYAxis.isEnabled = false
        val topXAxis = binding.graph.xAxis
        topXAxis.isEnabled = false

        val xAxis = binding.graph.xAxis
        xAxis.granularity = 3f
        xAxis.labelCount = 5
        xAxis.setCenterAxisLabels(true)
        xAxis.isEnabled = true
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        binding.graph.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)

        val data = LineData(dataSets)
        binding.graph.data = data
        binding.graph.invalidate()
        binding.graph.legend.isEnabled = false
        binding.graph.description.isEnabled = false
    }

    private fun getIncomeEntries1(): List<Entry> {
        val incomeEntries: ArrayList<Entry> = ArrayList()
        incomeEntries.apply {
            add(Entry(7F, 0F))
            add(Entry(8F, 0.1F))
            add(Entry(9F, 0.25F))
            add(Entry(10F, 0.45F))
            add(Entry(11F, 0.7F))
            add(Entry(12F, 1F))
            add(Entry(13F, 1.35F))
            add(Entry(14F, 1.75F))
            add(Entry(15F, 2.2F))
            add(Entry(16F, 2.7F))
            add(Entry(17F, 3.25F))
            add(Entry(18F, 3.85F))
            add(Entry(19F, 4.5F))
            add(Entry(20F, 5.3F))
        }

        return incomeEntries.subList(0, 14)
    }

    private fun getIncomeEntries2(): List<Entry> {
        val incomeEntries: ArrayList<Entry> = ArrayList()
        incomeEntries.apply {
            add(Entry(7F, 0F))
            add(Entry(8F, 0.2F))
            add(Entry(9F, 0.6F))
            add(Entry(10F, 1.2F))
            add(Entry(11F, 2F))
            add(Entry(12F, 3F))
            add(Entry(13F, 4.2F))
            add(Entry(14F, 5.6F))
            add(Entry(15F, 7.2F))
            add(Entry(16F, 9F))
            add(Entry(17F, 11F))
            add(Entry(18F, 13.2F))
            add(Entry(19F, 15.6F))
            add(Entry(20F, 18.0F))
        }

        return incomeEntries.subList(0, 14)
    }

    private fun getIncomeEntries3(): List<Entry> {
        val incomeEntries: ArrayList<Entry> = ArrayList()
        incomeEntries.apply {
            add(Entry(7F, 0F))
            add(Entry(8F, 0.5F))
            add(Entry(9F, 1.5F))
            add(Entry(10F, 3F))
            add(Entry(11F, 5F))
            add(Entry(12F, 7.5F))
            add(Entry(13F, 10.5F))
            add(Entry(14F, 14F))
            add(Entry(15F, 18F))
            add(Entry(16F, 22.5F))
            add(Entry(17F, 27.5F))
            add(Entry(18F, 33F))
            add(Entry(19F, 39F))
            add(Entry(20F, 45F))
        }

        return incomeEntries.subList(0, 14)
    }
}