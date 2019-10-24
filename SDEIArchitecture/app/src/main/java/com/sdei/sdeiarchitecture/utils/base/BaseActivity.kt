package com.sdei.sdeiarchitecture.utils.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sdei.sdeiarchitecture.repository.dagger.AppHelper
import com.sdei.sdeiarchitecture.repository.dagger.SharedPreferenceHelper
import com.sdei.sdeiarchitecture.repository.networkoperator.NetworkAdapter
import com.sdei.sdeiarchitecture.utils.handleApiError
import javax.inject.Inject

/**
 * Created by Vishal on 10/10/19.
 */

abstract class BaseActivity : AppCompatActivity() {
    // since its going to be common for all the activities
    var mViewModel: ViewModel? = null
    lateinit var mContext: Context

    abstract val mViewDataBinding: ViewDataBinding

    lateinit var binding: ViewDataBinding
    /**
     * @return toolbar resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract var viewModel: ViewModel

    /**
     *
     * @return context
     */
    protected abstract val context: Context

    @Inject
    lateinit var appHelper: AppHelper

    @Inject
    lateinit var prefHelper: SharedPreferenceHelper


    @Inject
    lateinit var networkAdapter: NetworkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }
        this.binding = mViewDataBinding
        this.mContext = context
        this.mViewModel = viewModel

        (applicationContext as AppApplication).appComponent.inject(this)

    }

    override fun onStart() {
        super.onStart()

        bindData()
        initListeners()
    }

    abstract fun bindData()

    abstract fun initListeners()

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Common function for Set Up Binding ...
     */
    fun setUpBinding(): ViewDataBinding {
        return DataBindingUtil.setContentView(this, layoutId)
    }

    /**
     * Common function for Set Up ViewModel...
     * if no ViewModel Available then use BaseModel ...
     * You can also send parameters in constructor ...
     */
    fun setUpVM(activity: AppCompatActivity, obj: ViewModel): ViewModel {
        val provider = AppVMProvider()
        provider.createParams(obj)
        return ViewModelProviders.of(
            activity, provider
        ).get(obj::class.java)
    }

    fun handleApiError(t: Throwable?, view: ViewGroup) {
        handleApiError(t, view, appHelper)
    }

    /*fun performDataBinding(): ViewDataBinding {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        // this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        // mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        //  mViewDataBinding.executePendingBindings()
        return mViewDataBinding
    }*/

}

