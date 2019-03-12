package com.sdei.sdeiarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sdei.sdeiarchitecture.BR
import com.sdei.sdeiarchitecture.callback.RecyclerListener

abstract class AppRecyclerBaseAdapter : RecyclerView.Adapter<AppRecyclerBaseAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Any, position: Int, recyclerListener: RecyclerListener?) {
            binding.setVariable(BR.item, obj)
            binding.setVariable(BR.position, position)
            binding.setVariable(BR.listener, recyclerListener)
            binding.executePendingBindings()
        }

    }

    /**
     * create a new RecyclerView.ViewHolder and initializes
     * some fields to be used by RecyclerView
     * @param parent
     * @param viewType
     * @return the holder
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return MyViewHolder(binding)
    }

    /**
     * update the RecyclerView.ViewHolder contents with the item at the given position
     * @param holder to update
     * @param position position on which holder to update
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.binding.root.tag = position
        holder.bind(obj, position, getRecyclerListener())
    }

    /**
     * @param position item position
     * @return the view type of the item at position for the purposes of view recycling
     */
    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    protected abstract fun getObjForPosition(position: Int): Any

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    protected abstract fun getRecyclerListener(): RecyclerListener?

}