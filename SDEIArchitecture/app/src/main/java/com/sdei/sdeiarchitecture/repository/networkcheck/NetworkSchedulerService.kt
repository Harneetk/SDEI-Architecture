package com.sdei.sdeiarchitecture.repository.networkcheck

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import android.content.Intent
import android.widget.Toast
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.content.IntentFilter


class NetworkSchedulerService: JobService(), ConnectivityReceiver.ConnectivityReceiverListener {

    private val TAG = NetworkSchedulerService::class.java.simpleName

    private var mConnectivityReceiver: ConnectivityReceiver? = null

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Service created")
        mConnectivityReceiver = ConnectivityReceiver(this)
    }


    /**
     * When the app's NetworkConnectionActivity is created, it starts this service. This is so that the
     * activity and this service can communicate back and forth. See "setUiCallback()"
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        return Service.START_NOT_STICKY
    }


    override fun onStartJob(params: JobParameters): Boolean {
        Log.i(TAG, "onStartJob$mConnectivityReceiver")
        registerReceiver(mConnectivityReceiver, IntentFilter(CONNECTIVITY_ACTION))
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Log.i(TAG, "onStopJob")
        unregisterReceiver(mConnectivityReceiver)
        return true
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        val message =
            if (isConnected) "Good! Connected to Internet" else "Sorry! Not connected to internet"
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }
}