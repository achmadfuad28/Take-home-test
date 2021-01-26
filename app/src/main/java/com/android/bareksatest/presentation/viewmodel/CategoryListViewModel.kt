package com.android.bareksatest.presentation.viewmodel

import androidx.databinding.ObservableField
import com.android.bareksatest.data.entities.CompareCategoryItem
import com.android.bareksatest.presentation.base.BaseViewModel

class CategoryListViewModel : BaseViewModel() {
    var bTextTitleCategory = ObservableField<String>()
    var bIsHaveTitle = ObservableField<Boolean>(false)

    fun resetData() {
        bTextTitleCategory.set(null)
        bIsHaveTitle.set(false)
    }

    fun setData(category: CompareCategoryItem) {
        if(!category.type.isNullOrEmpty()) {
            bIsHaveTitle.set(true)
            bTextTitleCategory.set(category.title)
        }
    }
}