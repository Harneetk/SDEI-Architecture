package com.sdei.sdeiarchitecture

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sdei.sdeiarchitecture.data.User
import com.sdei.sdeiarchitecture.databinding.ActivityMainBinding
import com.sdei.sdeiarchitecture.helper.navigate
import com.sdei.sdeiarchitecture.helper.showError

class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        val user = User()
        user.email = prefHelper["email", ""]!!
        user.password = prefHelper["password", ""]!!
        appHelper.setUser(user)

        binding.user = appHelper.getUser()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
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

        val message = "You have entered " + prefHelper["email", ""] + " and " + prefHelper["password", ""]
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
