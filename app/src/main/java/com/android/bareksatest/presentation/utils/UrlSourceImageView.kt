package com.android.bareksatest.presentation.utils

interface UrlSourceImageView {

    val loadingPlaceHolder: Int
    val errorPlaceHolder: Int

    fun setImageUrl(url: String)
}
