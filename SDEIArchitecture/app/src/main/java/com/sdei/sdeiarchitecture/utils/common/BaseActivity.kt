package com.sdei.sdeiarchitecture.utils.common

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.helper.dagger.AppHelper
import com.sdei.sdeiarchitecture.helper.dagger.SharedPreferenceHelper
import com.sdei.sdeiarchitecture.utils.handleApiError
import com.sdei.sdeiarchitecture.repository.networkOperator.NetworkAdapter
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    // If you want to use dagger ...
    @Inject
    lateinit var appHelper: AppHelper

    @Inject
    lateinit var prefHelper: SharedPreferenceHelper


    @Inject
    lateinit var networkAdapter: NetworkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as AppApplication).appComponent.inject(this)

        setUpVM()

    }

    override fun onStart() {
        super.onStart()

        bindData()
        bindClick()
    }

    fun handleApiError(t: Throwable?, view: ViewGroup) {
        handleApiError(t, view, appHelper)
    }

    /**
     * Setup ViewModel with custom parameters ...
     */
    abstract fun setUpVM(): ViewModel?

    abstract fun bindData()

    abstract fun bindClick()
}