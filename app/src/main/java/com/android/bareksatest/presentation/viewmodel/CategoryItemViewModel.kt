package com.android.bareksatest.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.android.bareksatest.data.entities.Company
import com.android.bareksatest.presentation.base.BaseViewModel
import com.android.bareksatest.presentation.utils.CommonUtils

class CategoryItemViewModel : BaseViewModel() {
    var bTextCategoryName = ObservableField<String>()
    var bIsHaveImage = ObservableField<Boolean>(false)
    var bIsShowButton = ObservableField<Boolean>(false)
    var bImageData = MutableLiveData<String>()

    fun resetData() {
        bTextCategoryName.set(null)
    }

    fun setData(category: Any, lastPosition: Boolean) {
        bIsShowButton.set(lastPosition)
        if(category is String) {
            bTextCategoryName.set(category)
        } else {
            val data = CommonUtils.convertSObjectToJsonClass(category, Company::class.java)
            data?.let {
                bIsHaveImage.set(true)
                bTextCategoryName.set(it.name)
                bImageData.value = it.imageUrl
            }

        }
    }
}