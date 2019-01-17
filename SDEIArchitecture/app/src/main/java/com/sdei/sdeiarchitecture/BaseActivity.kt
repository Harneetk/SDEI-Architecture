package com.sdei.sdeiarchitecture

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.sdei.sdeiarchitecture.helper.dagger.ApiHelper
import com.sdei.sdeiarchitecture.helper.dagger.AppHelper
import com.sdei.sdeiarchitecture.helper.dagger.SharedPreferenceHelper
import com.sdei.sdeiarchitecture.helper.handleApiError
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var appHelper: AppHelper

    @Inject
    lateinit var prefHelper: SharedPreferenceHelper

    @Inject
    lateinit var apiHelper: ApiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as AppApplication).appComponent.inject(this)
    }

    fun handleApiError(t: Throwable?, view: ViewGroup) {
        handleApiError(t, view, appHelper)
    }

}