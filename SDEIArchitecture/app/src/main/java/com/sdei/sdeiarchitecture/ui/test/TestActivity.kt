package com.sdei.sdeiarchitecture.ui.test

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.ActivityTestBinding
import com.sdei.sdeiarchitecture.ui.main.MainActivity
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.utils.base.BaseVM


class TestActivity : BaseActivity() {
    override var viewModel: ViewModel
        get() = setUpVM(this@TestActivity, BaseVM()) as BaseVM
        set(value) {}
    override val layoutId: Int
        get() = R.layout.activity_test
    override val context: Context
        get() = this
    override val mViewDataBinding: ActivityTestBinding
        get() = setUpBinding() as ActivityTestBinding


    val arr: ArrayList<String>? = null
    override fun bindData() {
        //  no implementations ...
        if (arr.isNullOrEmpty()) {

        }
    }

    override fun initListeners() {
        mViewDataBinding.button2.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }
}