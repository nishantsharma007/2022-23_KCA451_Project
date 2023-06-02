package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessDetailsFragment
import com.app.pepens.model.ProductsWithCategory.ProductsWithCategory
import kotlinx.android.synthetic.main.custom_business_category_layout.view.*

class BusinessItemsCategoryAdapter : RecyclerView.Adapter<BusinessItemsCategoryAdapter.ViewHolder> {

    private var context: Context
    private var productsWithCategory: ProductsWithCategory
    private var categoryBusinessDetailsFragment: CategoryBusinessDetailsFragment

    constructor(
        context: Context,
        productsWithCategory: ProductsWithCategory,
        categoryBusinessDetailsFragment: CategoryBusinessDetailsFragment
    ) {
        this.context = context
        this.productsWithCategory = productsWithCategory
        this.categoryBusinessDetailsFragment = categoryBusinessDetailsFragment
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_business_category_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return if (productsWithCategory!!.productsWithCategoryData == null) {
            0
        } else {
            productsWithCategory!!.productsWithCategoryData!!.count()
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = productsWithCategory!!.productsWithCategoryData!![position].itemCategory!!.itemCategoryName

        var layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        viewHolder.recyclerViewItems!!.layoutManager = layoutManager
        var businessItemsAdapter = BusinessItemsAdapter(
            context,
            productsWithCategory!!.productsWithCategoryData!![position].businessItems!!,
            categoryBusinessDetailsFragment
        )
        viewHolder.recyclerViewItems!!.adapter = businessItemsAdapter
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val recyclerViewItems: RecyclerView = view.recyclerViewItems
    }

}