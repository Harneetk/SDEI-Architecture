package com.sdei.sdeiarchitecture.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.adapter.AppRecyclerAdapter
import com.sdei.sdeiarchitecture.callback.RecyclerListener
import com.sdei.sdeiarchitecture.data.Country
import com.sdei.sdeiarchitecture.databinding.FragmentRecyclerItemsBinding
import com.sdei.sdeiarchitecture.helper.popBackFragment
import java.io.IOException

class RecyclerItemsPage : BaseFragment(), View.OnClickListener, RecyclerListener {

    private lateinit var binding: FragmentRecyclerItemsBinding

    private val countyList = ArrayList<Country>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recycler_items,
            container,
            false
        )
        binding.clickListener = this
        binding.recyclerListener = this
        countyList.addAll(getStaticDataItems())
        binding.items = countyList
        return binding.root
    }

    private fun getStaticDataItems(): ArrayList<Country> {
        val json: String? = loadJSONFromAsset()
        val mapper = ObjectMapper()
        val typeReference = object : TypeReference<ArrayList<Country>>() {}
        return mapper.readValue(json!!, typeReference)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_btn -> {
                popBackFragment(this@RecyclerItemsPage)
            }
        }
    }

    override fun onRecyclerItemClick(position: Int) {
        Toast.makeText(context!!, countyList[position].name, Toast.LENGTH_SHORT).show()
    }

    override fun onRecyclerItemClick(actionType: String) {
        Log.e("RecyclerItemsPage", "Clicked action: $actionType")
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream = activity!!.assets.open("countries.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            return json
        }
    }

}