package com.sdei.sdeiarchitecture.utils.base

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidadvance.topsnackbar.TSnackbar
import com.sdei.sdeiarchitecture.R

/**
 * Created by Vishal on 10/10/19.
 */

abstract class BaseActivity<myBinding : ViewDataBinding, V : ViewModel> : AppCompatActivity() {
    // since its going to be common for all the activities
    var mViewModel: ViewModel? = null
    lateinit var mContext: Context

    abstract val binding: myBinding

    lateinit var mViewBinding: ViewDataBinding
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
    abstract var viewModel: V

    /**
     *
     * @return context
     */
    protected abstract val context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }*/
        this.mViewBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mContext = context
        this.mViewModel = viewModel
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
    fun setUpBinding(): myBinding {
        return mViewBinding as myBinding
    }

    /**
     * Common function for Set Up ViewModel...
     * if no ViewModel Available then use BaseModel ...
     * You can also send parameters in constructor ...
     */
    fun setUpVM(activity: AppCompatActivity, obj: ViewModel): V {
        val provider = AppVMProvider()
        provider.createParams(obj)
        return ViewModelProvider(
            activity, provider
        ).get(obj::class.java) as V
    }


    /*fun performDataBinding(): ViewDataBinding {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        // this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        // mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        //  mViewDataBinding.executePendingBindings()
        return mViewDataBinding
    }*/

    /* Method for showing SnackBar Alert ...
    *
    * @param view
    * @param message
    * @param isError
    */
    fun showSnackBar(view: View, message: String, isError: Boolean) {
        val snackBar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        val snackBarView = snackBar.view
        when (isError) {
            true -> snackBarView.setBackgroundColor(Color.parseColor("#ff0000"))
            false -> snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
        }

//        val font = Typeface.createFromAsset(
//            assets,
//            "fonts/montserrat_regular.otf"
//        )

        val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        textView.setPadding(
            0,
            (getStatusBarHeight() + resources.getDimension(R.dimen._30sdp).toInt()),
            0,
            0
        )
        textView.gravity = Gravity.CENTER
        //textView.typeface = font
        textView.textSize = resources.getDimension(R.dimen._5sdp)
        snackBar.show()
    }

    /**
     * Get Status Bar Height for the device ...
     * @return
     */
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun openFragmentReplace(id: Int, fragment: Fragment, tag: String, addToBack: Boolean) {
        when (addToBack) {
            true -> supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .replace(id, fragment, tag)
                .addToBackStack(null)
                .commit()

            false -> supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .replace(id, fragment, tag)
                .commit()
        }
    }

    fun openFragmentReplaceNoAnim(
        id: Int,
        fragment: Fragment,
        tag: String,
        addToBack: Boolean
    ) {
        when (addToBack) {
            true -> supportFragmentManager
                .beginTransaction()
                .replace(id, fragment, tag)
                .addToBackStack(null)
                .commit()

            false -> supportFragmentManager
                .beginTransaction()
                .replace(id, fragment, tag)
                .commit()
        }
    }

}

