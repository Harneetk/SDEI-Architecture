package com.sdei.sdeiarchitecture.repository.networkOperator


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("GetCompanyForSetupAsync")
    fun getCompanyDetails(): Call<String>


}