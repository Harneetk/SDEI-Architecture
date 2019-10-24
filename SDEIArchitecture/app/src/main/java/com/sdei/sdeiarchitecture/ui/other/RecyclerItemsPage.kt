package com.sdei.sdeiarchitecture.ui.other

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.model.data.Country
import com.sdei.sdeiarchitecture.databinding.FragmentRecyclerItemsBinding
import com.sdei.sdeiarchitecture.utils.base.BaseFragment
import com.sdei.sdeiarchitecture.utils.base.BaseVM
import com.sdei.sdeiarchitecture.utils.common.recyclerviewbase.RecyclerBindingList
import com.sdei.sdeiarchitecture.utils.common.recyclerviewbase.RecyclerCallback
import com.sdei.sdeiarchitecture.utils.popBackFragment
import java.io.IOException

class RecyclerItemsPage : BaseFragment(), View.OnClickListener, RecyclerCallback {

    override val layoutId: Int
        get() = R.layout.fragment_recycler_items
    override var binding: ViewDataBinding
        get() = setUpBinding() as FragmentRecyclerItemsBinding
        set(value) {}
    override var viewModel: ViewModel
        get() = setUpVM(this@RecyclerItemsPage, BaseVM())
        set(value) {}


    private var bindList: RecyclerBindingList<Country> = RecyclerBindingList()
    private val countyList = ArrayList<Country>()


    private lateinit var viewBinding: FragmentRecyclerItemsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewBinding = binding as FragmentRecyclerItemsBinding
    }

    override fun bindData() {
        viewBinding.clickListener = this
        viewBinding.callback = this
        countyList.addAll(getStaticDataItems())
        bindList.itemsList = countyList
        viewBinding.items = bindList
    }


    override fun initListeners() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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