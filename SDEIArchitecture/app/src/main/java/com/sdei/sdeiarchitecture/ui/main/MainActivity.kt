package com.sdei.sdeiarchitecture.ui.main

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.ActivityMainBinding
import com.sdei.sdeiarchitecture.model.data.User
import com.sdei.sdeiarchitecture.ui.next.NextActivity
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.utils.base.BaseVM
import com.sdei.sdeiarchitecture.utils.checkMyPermission
import com.sdei.sdeiarchitecture.utils.navigate
import com.sdei.sdeiarchitecture.utils.showError

class MainActivity : BaseActivity(), View.OnClickListener {


    override var viewModel: ViewModel
        get() = setUpVM(this@MainActivity, BaseVM()) as BaseVM
        set(value) {}
    override val layoutId: Int
        get() = R.layout.activity_main
    override val context: Context
        get() = this
    override val mViewDataBinding: ActivityMainBinding
        get() = setUpBinding() as ActivityMainBinding


    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewBinding = binding as ActivityMainBinding
        checkUserPermission()

        val user = User()
        user.email = prefHelper["email", ""]!!
        user.password = prefHelper["password", ""]!!
        appHelper.setUser(user)

        viewBinding.user = appHelper.getUser()
    }

    /**
     *  For Marshmallow User Permissions ...
     */
    private fun checkUserPermission() {
        checkMyPermission(this)
    }

    override fun initListeners() {

    }

    /**
     * Override method ...
     */
    override fun bindData() {
        saveImage(null)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_tv -> {
                if (validInputs()) {
                    appHelper.hideKeyboard(this@MainActivity)
                    saveUserDetailsInPreference()
                    navigate<NextActivity>()
                }
            }
        }

    }

    private fun saveUserDetailsInPreference() {
        prefHelper.put("email", appHelper.getUser().email)
        prefHelper.put("password", appHelper.getUser().password)

        val message =
            "You have entered " + prefHelper["email", ""] + " and " + prefHelper["password", ""]
        appHelper.showSnackBarToast(message, viewBinding.parent)
    }

    private fun validInputs(): Boolean {
        return if (!appHelper.validEmail(appHelper.getUser().email)) {
            showError(getString(R.string.invalid_email), viewBinding.parent)
            false
        } else if (appHelper.getUser().password.isBlank()) {
            showError(getString(R.string.enter_password), viewBinding.parent)
            false
        } else if (appHelper.getUser().password.length < 6 || appHelper.getUser().password.length > 20) {
            showError(getString(R.string.invalid_password), viewBinding.parent)
            false
        } else {
            true
        }
    }

    private fun saveImage(bitmap: Bitmap?) = runWithPermissions(Permission.WRITE_EXTERNAL_STORAGE) {
        Log.e("saveImage", "" + it.isAllGranted())
        Log.e("saveImage", "Bitmap Is : " + (bitmap == null))
    }

}
