package com.sdei.sdeiarchitecture.ui.test

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.ActivityTest2Binding
import com.sdei.sdeiarchitecture.utils.base.BaseVM

class TestActivity2 : BaseActivity() {
    override var viewModel: ViewModel
        get() = setUpVM(this@TestActivity2, BaseVM()) as BaseVM
        set(value) {}
    override val layoutId: Int
        get() = R.layout.activity_test_2
    override val context: Context
        get() = this
    override val mViewDataBinding: ActivityTest2Binding
        get() = setUpBinding() as ActivityTest2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callFragment()
    }

    override fun bindData() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListeners() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun callFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.parent2, TestFragment(), "")
            .addToBackStack(null)
            .commit()
    }
}