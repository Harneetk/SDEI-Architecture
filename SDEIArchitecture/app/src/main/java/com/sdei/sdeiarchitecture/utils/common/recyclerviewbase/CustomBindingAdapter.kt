@file:Suppress("NAME_SHADOWING")

package com.sdei.sdeiarchitecture.utils.common.recyclerviewbase

import android.content.Context
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Vishal Sharma on 2020-01-15.
 */

@BindingAdapter(
    "recyclerLinearAdapter", "layout", "onItemClickListener", "viewModel", "itemDecor", "horizontal", "grid", "numColumn", "subViewAdapter", "context", "layoutManager",
    requireAll = false
)
fun setRecyclerLinearAdapter(
    view: RecyclerView,
    list: RecyclerBindingList<*>?,
    layout: Int,
    callback: RecyclerCallback?,
    viewModel: Any?,
    itemDecor: Boolean,
    horizontal: Boolean,
    isGrid: Boolean,
    numColumn: Int,
    subViewAdapter: Any?,
    context: Context?,
    manager: LinearLayoutManager?
) {
    var numColumn = numColumn
    val layoutManager: LinearLayoutManager
    if (manager != null) {
        layoutManager = manager
    } else {
        when {
            horizontal -> {
                layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            }
            isGrid -> {
                if (numColumn == 0) {
                    numColumn = 2
                }
                layoutManager = GridLayoutManager(view.context, numColumn)
            }
            else -> {
                layoutManager = LinearLayoutManager(view.context)
            }
        }
    }
    view.layoutManager = layoutManager
    if (itemDecor) {
        view.addItemDecoration(
            SimpleDividerItemDecoration(
                view.context
            )
        )
    }
    view.itemAnimator = DefaultItemAnimator()
    Log.e("MyList", "List : " + list?.itemsList?.size)
    Log.e("MyList", "callback : " + (callback != null))
    if (list != null) {
        val adapter = MyRecyclerAdapter(
            layout, list.itemsList, callback, viewModel,
            subViewAdapter, context
        )
        view.adapter = adapter
        list.adapter = adapter
    }
}
