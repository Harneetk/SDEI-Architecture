package com.sdei.sdeiarchitecture.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<dataBinding : ViewDataBinding, viewModel : ViewModel> : Fragment() {
    // since its going to be common for all the activities
    private var mViewModel: ViewModel? = null
    lateinit var baseActivity: BaseActivity<dataBinding, viewModel>

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
    abstract var viewModel: viewModel

    abstract var binding: dataBinding

    lateinit var mViewDataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity<dataBinding, viewModel>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        this.mViewModel = viewModel
        return mViewDataBinding.root
    }


    override fun onStart() {
        super.onStart()
        bindData()
        initListeners()
    }

    protected abstract fun bindData()

    protected abstract fun initListeners()

    fun setUpBinding(): dataBinding {
        return mViewDataBinding as dataBinding
    }

    /**
     * Common function for Set Up ViewModel...
     * if no ViewModel Available then use BaseModel ...
     * You can also send parameters in constructor ...
     */
    fun setUpVM(fragment: Fragment, obj: ViewModel): viewModel {
        val provider = AppVMProvider()
        provider.createParams(obj)
        return ViewModelProvider(
            fragment, provider
        ).get(obj::class.java) as viewModel
    }

    fun showSnackBar(view: View, message: String, isError: Boolean) {
        baseActivity.showSnackBar(view, message, isError)
    }

    fun openFragmentReplace(id: Int, fragment: Fragment, tag: String, addToBack: Boolean) {
        baseActivity.openFragmentReplace(id, fragment, tag, addToBack)
    }

    fun openFragmentReplaceNoAnim(id: Int, fragment: Fragment, tag: String, addToBack: Boolean) {
        baseActivity.openFragmentReplaceNoAnim(id, fragment, tag, addToBack)
    }

    fun onBackPressed() {
        baseActivity.onBackPressed()
//        mContext.supportFragmentManager.popBackStack()
    }
}