package com.sdei.sdeiarchitecture.helper

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdei.sdeiarchitecture.adapter.AppRecyclerAdapter
import com.sdei.sdeiarchitecture.callback.RecyclerListener

@BindingAdapter("recyclerViewAdapter", "layout", "recyclerListener")
fun <T> setRecyclerLinearAdapter(
    view: RecyclerView,
    items: ArrayList<T>,
    layout: Int,
    recyclerListener: RecyclerListener
) {
    val mLayoutManager = LinearLayoutManager(view.context)
    view.layoutManager = mLayoutManager
    view.itemAnimator = DefaultItemAnimator()

    view.adapter = AppRecyclerAdapter(layout, items, recyclerListener)
}
