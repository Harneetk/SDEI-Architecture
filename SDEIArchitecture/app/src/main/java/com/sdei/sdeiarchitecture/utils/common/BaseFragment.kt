package com.sdei.sdeiarchitecture.utils.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment : Fragment() {

    fun handleApiError(t: Throwable?, view: ViewGroup) {
        (context as BaseActivity).handleApiError(t, view)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpVM()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()
        bindClick()
    }

    /**
     * Setup ViewModel with custom parameters ...
     */
    abstract fun setUpVM(): ViewModel?

    abstract fun bindData()

    abstract fun bindClick()
}