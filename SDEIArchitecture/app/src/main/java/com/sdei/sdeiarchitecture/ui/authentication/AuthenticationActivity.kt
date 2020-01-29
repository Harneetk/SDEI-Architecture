package com.sdei.sdeiarchitecture.ui.authentication

import android.content.Context
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.ActivityAuthenticationBinding
import com.sdei.sdeiarchitecture.ui.authentication.login.LoginFragment
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.utils.base.BaseVM
import com.sdei.sdeiarchitecture.utils.checkMyPermission

/**
 * Created by Vishal Sharma on 2019-12-06.
 */
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding, BaseVM>() {
    override val binding: ActivityAuthenticationBinding
        get() = setUpBinding()
    override val layoutId: Int
        get() = R.layout.activity_authentication
    override var viewModel: BaseVM
        get() = setUpVM(
            this,
            BaseVM(application)
        )
        set(value) {}
    override val context: Context
        get() = this


    override fun bindData() {

        checkMyPermission(this)
        openFragmentReplaceNoAnim(R.id.authContainer, LoginFragment(), "", true)
    }

    override fun initListeners() {


    }
}