package com.sdei.sdeiarchitecture.ui.test

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.FragmentTestBinding
import com.sdei.sdeiarchitecture.utils.base.BaseFragment


class TestFragment : BaseFragment() {
    override var viewModel: ViewModel
        get() = setUpVM(this@TestFragment, TestVM())
        set(value) {}
    override var binding: ViewDataBinding
        get() = setUpBinding()
        set(value) {}
    override val layoutId: Int
        get() = R.layout.fragment_test

    private lateinit var myBinding: FragmentTestBinding


    override fun bindData() {
        myBinding = binding as FragmentTestBinding
        myBinding.textView2.text = "My Binding Test"
    }

    override fun initListeners() {
        //  no implementations ...
    }
}