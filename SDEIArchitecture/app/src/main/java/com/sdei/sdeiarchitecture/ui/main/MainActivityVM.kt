package com.sdei.sdeiarchitecture.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.repository.networkOperator.NetworkAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivityVM(var networkAdapter: NetworkAdapter) : ViewModel() {

    var apiResponse: MutableLiveData<String> = MutableLiveData()

    fun hitApi() {
        Log.e("MainActivityVM", "Param Is : ${networkAdapter.demo()}")
        networkAdapter.apiService.getCompanyDetails().enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                when (t) {

                }

            }
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                response.body()?.let {
                    apiResponse.value = it
                } ?: run {

                }
            }
        })
    }
}