package com.android.bareksatest.presentation.base

import androidx.recyclerview.widget.RecyclerView
import com.android.bareksatest.presentation.base.view.BaseViewHolder

abstract class BaseRecycleViewAdapter<T>() : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data: MutableList<T>

    init {
        data = ArrayList()
    }

    fun setData(data: List<T>) {
        this.data = data as MutableList<T>
        notifyDataSetChanged()
    }

    fun getData():MutableList<T>{
        return this.data
    }

    fun addData(data: List<T>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(getItem(position))
    }
}
