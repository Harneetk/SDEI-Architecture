package com.sdei.sdeiarchitecture.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.repository.networkoperator.NetworkAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivityVM : ViewModel() {

    var apiResponse: MutableLiveData<String> = MutableLiveData()

    fun hitApi() {
        NetworkAdapter.getInstance().getNetworkServices()?.getCompanyDetails()
            ?.enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {

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

    fun checkString(): String {
        return "Hello ViewModel"
    }



}