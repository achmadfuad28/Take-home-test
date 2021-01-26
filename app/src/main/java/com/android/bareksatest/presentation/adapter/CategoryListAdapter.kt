package com.android.bareksatest.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.android.bareksatest.R
import com.android.bareksatest.data.entities.CompareCategoryItem
import com.android.bareksatest.databinding.ItemListCategoryBinding
import com.android.bareksatest.presentation.base.BaseRecycleViewAdapter
import com.android.bareksatest.presentation.base.owner.ViewDataBindingOwner
import com.android.bareksatest.presentation.base.view.BaseViewHolder
import com.android.bareksatest.presentation.view.CategoryListView
import com.android.bareksatest.presentation.viewmodel.CategoryListViewModel

class CategoryListAdapter : BaseRecycleViewAdapter<CompareCategoryItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CompareCategoryItem> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ListViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<CompareCategoryItem>, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_list_category
    }

    class ListViewHolder(context: Context, view: View) :
        CategoryListView,
        BaseViewHolder<CompareCategoryItem>(context, view),
        ViewDataBindingOwner<ItemListCategoryBinding> {

        override lateinit var binding: ItemListCategoryBinding
        override lateinit var compareCategoryItemAdapter: CategoryItemAdapter
        override lateinit var compareCategoryItemLayoutManager: GridLayoutManager

        private lateinit var data: CompareCategoryItem
        private var viewModel: CategoryListViewModel? = null

        init {
            binding.vm = CategoryListViewModel()
            binding.view = this
            viewModel = binding.vm
        }

        override fun bindData(data: CompareCategoryItem) {
            this.data = data
            compareCategoryItemAdapter = CategoryItemAdapter(data.lastPosition)
            compareCategoryItemLayoutManager = GridLayoutManager(context, 3)
            viewModel?.resetData()
            viewModel?.setData(data)
            data.content?.let {
                compareCategoryItemAdapter.setData(it)
            }
        }

        override fun onClickItem(view: View) {

        }
    }

}