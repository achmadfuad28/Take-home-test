package com.android.bareksatest.presentation.view

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.android.bareksatest.presentation.adapter.CategoryItemAdapter

interface CategoryListView {
    var compareCategoryItemAdapter: CategoryItemAdapter
    var compareCategoryItemLayoutManager: GridLayoutManager


    fun onClickItem(view: View)
}