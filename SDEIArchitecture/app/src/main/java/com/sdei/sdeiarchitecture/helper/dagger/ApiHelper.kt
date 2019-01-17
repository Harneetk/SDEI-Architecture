package com.sdei.sdeiarchitecture.helper.dagger

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.BuildConfig.*
import com.android.volley.toolbox.JsonObjectRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.sdei.sdeiarchitecture.ApiCallback
import com.sdei.sdeiarchitecture.BuildConfig
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import com.sdei.sdeiarchitecture.volley.SingletonRequestQueue
import org.json.JSONObject

@AppScope
class ApiHelper(var context: Context) {

    val TAG = "ApiHelper"
    var mRequestQueue: SingletonRequestQueue? = null

    init {
        mRequestQueue = SingletonRequestQueue.getInstance(context)
    }

    companion object {
        val POST_API = "/api/users"
    }

    inline fun <reified T> post(uri: String, jsonObject: JSONObject, callback: ApiCallback<T>) {

        var statusCode = 1001 //Will be default value

        val responseListener = Response.Listener<JSONObject> { response ->
            val mapper = ObjectMapper()
            val userInfo = mapper.readValue(response.toString(), T::class.java)
            callback.apiSuccess(statusCode, userInfo)
        }

        val apiErrorListener = Response.ErrorListener { error ->
            if (error is NetworkError) {
                callback.onNetworkError(error)
            } else {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                callback.apiError(error)
            }
        }

        val request = object : JsonObjectRequest(uri, jsonObject, responseListener, apiErrorListener) {

            override fun getMethod(): Int {
                return Request.Method.POST
            }

            override fun getPriority(): Request.Priority {
                return Request.Priority.NORMAL
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
                statusCode = response!!.statusCode
                return super.parseNetworkResponse(response)
            }

            override fun parseNetworkError(volleyError: VolleyError?): VolleyError {
                if (volleyError?.networkResponse != null) {
                    statusCode = volleyError.networkResponse.statusCode
                    showLog(TAG, "Api Error = $statusCode")
                    showLog(TAG, "Api Error Data = " + String(volleyError.networkResponse.data))
                }
                return super.parseNetworkError(volleyError)
            }

        }

        callback.onApiStart()

        mRequestQueue!!.getRequestQueue()!!.add(request)

    }

    fun showLog(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

    inline fun <reified T> get(uri: String, callback: ApiCallback<T>) {

        var statusCode = 1001 //Will be default value

        val responseListener = Response.Listener<JSONObject> { response ->
            Log.e("ApiHelper", "" + response.toString())
            val mapper = ObjectMapper()
            val userInfo = mapper.readValue(response.toString(), T::class.java)
            callback.apiSuccess(statusCode, userInfo)
        }

        val apiErrorListener = Response.ErrorListener { error ->
            if (error is NetworkError) {
                callback.onNetworkError(error)
            } else {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                callback.apiError(error)
            }
        }

        val request = object : JsonObjectRequest(uri, null, responseListener, apiErrorListener) {
            override fun getMethod(): Int {
                return Request.Method.GET
            }

            override fun getPriority(): Request.Priority {
                return Request.Priority.IMMEDIATE
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
                statusCode = response!!.statusCode
                return super.parseNetworkResponse(response)
            }

            override fun parseNetworkError(volleyError: VolleyError?): VolleyError {
                if (volleyError?.networkResponse != null) {
                    statusCode = volleyError.networkResponse.statusCode
                    showLog(TAG, "Api Error = $statusCode")
                    showLog(TAG, "Api Error Data = " + String(volleyError.networkResponse.data))
                }
                return super.parseNetworkError(volleyError)
            }

        }

        callback.onApiStart()

        mRequestQueue!!.getRequestQueue()!!.add(request)

    }

}