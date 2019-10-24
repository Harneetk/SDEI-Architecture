package com.sdei.sdeiarchitecture.repository.networkoperator


import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("GetCompanyForSetupAsync")
    fun getCompanyDetails(): Call<String>


}