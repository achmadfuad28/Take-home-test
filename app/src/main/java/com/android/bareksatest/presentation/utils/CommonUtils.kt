package com.android.bareksatest.presentation.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class CommonUtils {
    companion object {
        private const val utf = "UTF-8"

        fun <T> convertStreamToJsonClass(aContext: Context, aFileName: String, convertClass: Class<T>): T? {
            val inputStream: InputStream?
            try {
                inputStream = aContext.assets.open(aFileName)
                val reader = BufferedReader(InputStreamReader(inputStream!!, utf))
                return Gson().fromJson(reader, convertClass)
            } catch (e: Exception) {
                Log.e(TAG, "Error: ", e)
            }

            return null
        }

        fun <T> convertSObjectToJsonClass(data: Any, convertClass: Class<T>): T? {
            try {
                return Gson().fromJson(Gson().toJson(data), convertClass)
            } catch (e: Exception) {
                Log.e(TAG, "Error: ", e)
            }

            return null
        }
    }
}