package com.sdei.sdeiarchitecture.callback

interface RecyclerListener {
    fun onRecyclerItemClick(position: Int)
    fun onRecyclerItemClick(actionType: String)
}