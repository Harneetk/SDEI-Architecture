package com.sdei.sdeiarchitecture.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.model.data.User
import com.sdei.sdeiarchitecture.databinding.ActivityMainBinding
import com.sdei.sdeiarchitecture.utils.navigate
import com.sdei.sdeiarchitecture.utils.showError
import com.sdei.sdeiarchitecture.ui.next.NextActivity
import com.sdei.sdeiarchitecture.utils.SAMPLE
import com.sdei.sdeiarchitecture.utils.common.AppVMProvider
import com.sdei.sdeiarchitecture.utils.common.BaseActivity

class MainActivity : BaseActivity(), View.OnClickListener {


    private lateinit var binding: ActivityMainBinding
    private var viewModel: MainActivityVM? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main)

        val user = User()
        user.email = prefHelper["email", ""]!!
        user.password = prefHelper["password", ""]!!
        appHelper.setUser(user)

        binding.user = appHelper.getUser()
    }

    /**
     * View Model Setup with parameters ...
     */
    override fun setUpVM(): ViewModel? {
        val provider = AppVMProvider()
        provider.createParams(MainActivityVM(networkAdapter))

        return ViewModelProviders.of(
            this, provider
        ).get(MainActivityVM::class.java)
    }

    /**
     * Override method ...
     */
    override fun bindData() {

    }

    override fun bindClick() {

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
        appHelper.showSnackBarToast(message, binding.parent)
    }

    private fun validInputs(): Boolean {
        return if (!appHelper.validEmail(appHelper.getUser().email)) {
            showError(getString(R.string.invalid_email), binding.parent)
            false
        } else if (appHelper.getUser().password.isBlank()) {
            showError(getString(R.string.enter_password), binding.parent)
            false
        } else if (appHelper.getUser().password.length < 6 || appHelper.getUser().password.length > 20) {
            showError(getString(R.string.invalid_password), binding.parent)
            false
        } else {
            true
        }
    }

}
