package com.sdei.sdeiarchitecture.ui.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.model.data.Country
import com.sdei.sdeiarchitecture.databinding.FragmentRecyclerItemsBinding
import com.sdei.sdeiarchitecture.utils.popBackFragment
import com.sdei.sdeiarchitecture.utils.common.BaseFragment
import com.sdei.sdeiarchitecture.utils.common.recyclerViewBase.RecyclerBindingList
import com.sdei.sdeiarchitecture.utils.common.recyclerViewBase.RecyclerCallback
import java.io.IOException

class RecyclerItemsPage : BaseFragment(), View.OnClickListener, RecyclerCallback {


    private lateinit var binding: FragmentRecyclerItemsBinding
    private var bindList: RecyclerBindingList<Country> = RecyclerBindingList()
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

        return binding.root
    }

    override fun setUpVM(): ViewModel? {
        return null
    }

    override fun bindData() {
        binding.clickListener = this
        binding.callback = this
        countyList.addAll(getStaticDataItems())
        bindList.itemsList = countyList
        binding.items = bindList
    }

    override fun bindClick() {

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


    /**
     * RecyclerView Item Click ...
     */
    override fun onItemClick(view: View?, position: Int) {
        Toast.makeText(context!!, countyList[position].name, Toast.LENGTH_SHORT).show()
    }

    override fun onChildItemClick(view: View?, parentIndex: Int, childIndex: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemAction(type: String?, position: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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