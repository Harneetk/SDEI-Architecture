package com.sdei.sdeiarchitecture.volley

import android.annotation.SuppressLint
import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class SingletonRequestQueue(private var context: Context){

    private var mRequestQueue: RequestQueue? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var mInstance: SingletonRequestQueue? = null
        fun getInstance(context: Context): SingletonRequestQueue = mInstance ?: synchronized(this) {
            mInstance ?: createInstance(context).also { mInstance = it}
        }
        private fun createInstance(context: Context) = SingletonRequestQueue(context)

    }

    init {
        mRequestQueue = getRequestQueue()
    }

    fun getRequestQueue(): RequestQueue? {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context)
        }
        return mRequestQueue
    }

}