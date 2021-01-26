package com.android.bareksatest.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.bareksatest.R
import com.android.bareksatest.databinding.ItemCategoryBinding
import com.android.bareksatest.presentation.base.BaseRecycleViewAdapter
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.view.BaseViewHolder
import com.android.bareksatest.presentation.utils.WebImageView
import com.android.bareksatest.presentation.view.CategoryItemView
import com.android.bareksatest.presentation.viewmodel.CategoryItemViewModel

class CategoryItemAdapter(var lastPosition: Boolean = false) : BaseRecycleViewAdapter<Any>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ListViewHolder(parent.context, view, lastPosition)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_category
    }

    class ListViewHolder(context: Context, view: View, lastPosition: Boolean) :
        CategoryItemView,
        BaseViewHolder<Any>(context, view),
        ViewDataBindingOwner<ItemCategoryBinding> {

        override lateinit var binding: ItemCategoryBinding
        private lateinit var data: Any
        private var viewModel: CategoryItemViewModel? = null
        private var lastPosition: Boolean = false

        init {
            binding.vm = CategoryItemViewModel()
            binding.view = this
            viewModel = binding.vm
            this.lastPosition = lastPosition
        }

        override fun bindData(data: Any) {
            when {
                adapterPosition % 3 == 0 -> {
                    binding.clItem.setBackgroundResource(R.drawable.bg_green)
                }
                adapterPosition % 3 == 1 -> {
                    binding.clItem.setBackgroundResource(R.drawable.bg_violet)
                }
                else -> {
                    binding.clItem.setBackgroundResource(R.drawable.bg_navy)
                }
            }
            this.data = data
            viewModel?.resetData()
            viewModel?.setData(data, lastPosition)
            viewModel?.bImageData?.value?.let {
                binding.imgProfile.setImageUrl(it, WebImageView.TransformType.CIRCLE)
            }

        }

        override fun onClickItem(view: View) {

        }
    }

}