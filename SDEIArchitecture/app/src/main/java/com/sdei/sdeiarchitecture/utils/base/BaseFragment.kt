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
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment : Fragment() {
    // since its going to be common for all the activities
    private var mViewModel: ViewModel? = null
    lateinit var mContext: BaseActivity

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

    abstract var binding: ViewDataBinding

    lateinit var mViewDataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity as BaseActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        bindData()
        initListeners()
    }

    protected abstract fun bindData()

    protected abstract fun initListeners()

    fun setUpBinding(): ViewDataBinding {
        return mViewDataBinding
    }

    /**
     * Common function for Set Up ViewModel...
     * if no ViewModel Available then use BaseModel ...
     * You can also send parameters in constructor ...
     */
    fun setUpVM(fragment: Fragment, obj: ViewModel): ViewModel {
        val provider = AppVMProvider()
        provider.createParams(obj)
        return ViewModelProviders.of(
            fragment, provider
        ).get(obj::class.java)
    }
}