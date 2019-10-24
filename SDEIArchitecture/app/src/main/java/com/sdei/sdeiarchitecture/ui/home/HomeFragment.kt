package com.sdei.sdeiarchitecture.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.FragmentHomeBinding
import com.sdei.sdeiarchitecture.repository.dagger.AppHelper
import com.sdei.sdeiarchitecture.ui.other.RecyclerItemsPage
import com.sdei.sdeiarchitecture.utils.base.BaseFragment
import com.sdei.sdeiarchitecture.utils.base.BaseVM
import com.sdei.sdeiarchitecture.utils.openFragment

class HomeFragment : BaseFragment(), View.OnClickListener {
    override val layoutId: Int
        get() = R.layout.fragment_home
    override var binding: ViewDataBinding
        get() = setUpBinding() as FragmentHomeBinding
        set(value) {}
    override var viewModel: ViewModel
        get() = setUpVM(this@HomeFragment, BaseVM())
        set(value) {}


    private lateinit var viewBinding: FragmentHomeBinding

    private lateinit var appHelper: AppHelper

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = binding as FragmentHomeBinding
        viewBinding.listener = this
    }

    override fun bindData() {

    }

    override fun initListeners() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.list_btn -> {
                openFragment(R.id.dashboard_fragment_container, RecyclerItemsPage())
            }
            R.id.post_request_btn -> {

            }
            R.id.get_request_btn -> {

            }
            R.id.retrieve_db -> {
                /*val userInfo = appHelper.getUserInfo(context!!)
                if (userInfo == null) {
                    Toast.makeText(context, getString(R.string.no_record_found), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, userInfo.name, Toast.LENGTH_SHORT).show()
                }*/
            }
            R.id.delete_btn -> {
//                val userInfo = appHelper.getUserInfo(context!!)
//                appHelper.deleteUserInfo(context!!, userInfo)
            }
        }

    }


}