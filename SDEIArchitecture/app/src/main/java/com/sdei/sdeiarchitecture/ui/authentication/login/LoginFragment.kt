package com.sdei.sdeiarchitecture.ui.authentication.login

import androidx.lifecycle.Observer
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.FragmentLoginBinding
import com.sdei.sdeiarchitecture.utils.base.BaseFragment

/**
 * Created by Vishal Sharma on 2019-12-06.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginVM>() {
    override val layoutId: Int
        get() = R.layout.fragment_login
    override var viewModel: LoginVM
        get() = setUpVM(this, LoginVM(baseActivity.application))
        set(value) {}
    override var binding: FragmentLoginBinding
        get() = setUpBinding()
        set(value) {}

    override fun bindData() {

        binding.vm = viewModel
        viewModel.observerError()?.observe(this, Observer {
            showSnackBar(binding.root, it.toString(), true)
        })

        viewModel.observerProgress()?.observe(this, Observer {

        })
    }

    override fun initListeners() {

    }
}