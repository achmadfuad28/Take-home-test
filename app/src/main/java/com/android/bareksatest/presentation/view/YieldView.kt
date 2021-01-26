package com.android.bareksatest.presentation.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bareksatest.presentation.adapter.CategoryListAdapter
import com.android.bareksatest.presentation.base.view.LifecycleView

interface YieldView : LifecycleView {
    var compareCategoryAdapter: CategoryListAdapter
    var compareCategoryLayoutManager: LinearLayoutManager
}