package com.sdei.sdeiarchitecture

import com.android.volley.VolleyError

interface ApiCallback<in T>{
    fun apiSuccess(statusCode: Int, t: T?)
    fun apiError(t: Throwable?)
    fun onApiStart()
    fun onNetworkError(error: VolleyError)
}