package com.sdei.sdeiarchitecture.ui.home

import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.FragmentHomeBinding
import com.sdei.sdeiarchitecture.utils.base.BaseFragment
import com.sdei.sdeiarchitecture.utils.base.BaseVM

/**
 * Created by Vishal Sharma on 2020-01-24.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, BaseVM>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    override var viewModel: BaseVM
        get() = setUpVM(this, BaseVM(baseActivity.application))
        set(value) {}
    override var binding: FragmentHomeBinding
        get() = setUpBinding()
        set(value) {}

    override fun bindData() {

    }

    override fun initListeners() {

    }
}