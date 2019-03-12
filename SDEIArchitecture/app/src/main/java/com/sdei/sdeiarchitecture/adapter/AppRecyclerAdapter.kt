package com.sdei.sdeiarchitecture.adapter

import com.sdei.sdeiarchitecture.callback.RecyclerListener
import java.util.ArrayList

class AppRecyclerAdapter<T>() : AppRecyclerBaseAdapter() {

    private var items: ArrayList<T>? = ArrayList()
    private var resourceId: Int = 0
    private var recyclerListener: RecyclerListener? = null

    constructor(_resourceId: Int,
                _items: java.util.ArrayList<T>?,
                _recyclerListener: RecyclerListener) : this() {
        this.items = _items
        this.resourceId = _resourceId
        this.recyclerListener = _recyclerListener
    }

    override fun getItemCount(): Int = items!!.size

    override fun getObjForPosition(position: Int): Any = items!![position] as Any

    override fun getLayoutIdForPosition(position: Int): Int = resourceId

    override fun getRecyclerListener(): RecyclerListener? = recyclerListener

}