package com.sdei.sdeiarchitecture.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.volley.VolleyError
import com.sdei.sdeiarchitecture.*
import com.sdei.sdeiarchitecture.data.UserInfo
import com.sdei.sdeiarchitecture.data.UserListResponse
import com.sdei.sdeiarchitecture.databinding.FragmentHomeBinding
import com.sdei.sdeiarchitecture.helper.dagger.ApiHelper
import com.sdei.sdeiarchitecture.helper.dagger.AppHelper
import org.json.JSONException
import org.json.JSONObject

class HomeFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var appHelper: AppHelper
    private lateinit var apiHelper: ApiHelper

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        apiHelper = (context as NextActivity).apiHelper
        appHelper = (context).appHelper
        this.activityContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.listener = this
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.post_request_btn -> {
                postRequest()
            }
            R.id.get_request_btn -> {
                getRequest()
            }
            R.id.retrieve_db -> {
                val userInfo = appHelper.getUserInfo(context!!)
                if (userInfo == null) {
                    Toast.makeText(context, getString(R.string.no_record_found), Toast.LENGTH_SHORT).show()
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

    private fun getRequest() {

        val uri: String = BuildConfig.BASE_URL + ApiHelper.POST_API + "?page=2"

        apiHelper.get(uri, object : ApiCallback<UserListResponse> {

            override fun apiSuccess(status: Int, t: UserListResponse?) {
                if (status == 1001 || status == 201) {
                    appHelper.hideProgressDialog()
                    Toast.makeText(context, "" + t!!.data.size + " records received", Toast.LENGTH_SHORT).show()
                }
            }

            override fun apiError(t: Throwable?) {
                appHelper.hideProgressDialog()
                handleApiError(t, binding.parent)
            }

            override fun onApiStart() {
                appHelper.showProgressDialog(context as BaseActivity)
            }

            override fun onNetworkError(error: VolleyError) {
                appHelper.hideProgressDialog()
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun postRequest() {

        val jsonObject = JSONObject()
        try {
            jsonObject.put("name", "JournalDev.com")
            jsonObject.put("job", "To teach you the best")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val uri: String = BuildConfig.BASE_URL + ApiHelper.POST_API

        apiHelper.post(uri, jsonObject, object : ApiCallback<UserInfo> {
            override fun apiSuccess(status: Int, t: UserInfo?) {
                if (status == 1001 || status == 201) {
                    appHelper.hideProgressDialog()
                    Toast.makeText(context, t!!.name, Toast.LENGTH_SHORT).show()
                    appHelper.saveUserInfo(activityContext, t)
                }
            }

            override fun apiError(t: Throwable?) {
                appHelper.hideProgressDialog()
                handleApiError(t, binding.parent)
            }

            override fun onApiStart() {
                appHelper.showProgressDialog(context as BaseActivity)
            }

            override fun onNetworkError(error: VolleyError) {
                appHelper.hideProgressDialog()
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show()
            }

        })

    }

}