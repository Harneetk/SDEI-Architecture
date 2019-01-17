package com.sdei.sdeiarchitecture.fragment

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdei.sdeiarchitecture.BaseActivity

open class BaseFragment : Fragment() {

    fun handleApiError(t: Throwable?, view: ViewGroup) {
        (context as BaseActivity).handleApiError(t, view)
    }

}