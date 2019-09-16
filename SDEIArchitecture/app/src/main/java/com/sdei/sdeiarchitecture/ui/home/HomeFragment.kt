package com.sdei.sdeiarchitecture.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.*
import com.sdei.sdeiarchitecture.databinding.FragmentHomeBinding
import com.sdei.sdeiarchitecture.ui.other.RecyclerItemsPage
import com.sdei.sdeiarchitecture.helper.dagger.AppHelper
import com.sdei.sdeiarchitecture.utils.openFragment
import com.sdei.sdeiarchitecture.utils.common.BaseFragment

class HomeFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var appHelper: AppHelper

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.listener = this
        return binding.root
    }

    override fun setUpVM(): ViewModel? {
        return null
    }

    override fun bindData() {

    }

    override fun bindClick() {

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
                val userInfo = appHelper.getUserInfo(context!!)
                if (userInfo == null) {
                    Toast.makeText(context, getString(R.string.no_record_found), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, userInfo.name, Toast.LENGTH_SHORT).show()
                }
            }
            R.id.delete_btn -> {
                val userInfo = appHelper.getUserInfo(context!!)
                appHelper.deleteUserInfo(context!!, userInfo)
            }
        }

    }


}