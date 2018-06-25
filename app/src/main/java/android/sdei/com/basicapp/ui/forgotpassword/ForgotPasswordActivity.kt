package android.sdei.com.basicapp.ui.forgotpassword

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sdei.com.basicapp.BaseActivity
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.databinding.ActivityForgotPasswordBinding

import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.ui.forgotpassword.ForgotPasswordViewModel
import android.view.Menu
import android.view.View



/*
*  This activity send  forgot password request
* */

class ForgotPasswordActivity : BaseActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    lateinit var viewModel: ForgotPasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
        binding.viewModel=viewModel
//        binding.resetPassword.setOnClickListener(View.OnClickListener {
//            hideSoftKeyboard(this,binding.emailLayout.editText!!)
//            viewModel.onForgotPassword(it)
//        })

//       setUpToolBar(binding.include.toolbar, binding.include.title, getString(R.string.title_forgot_password),false)
       attachObserver()
    }





    private fun attachObserver() {
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })
        viewModel.apiError.observe(this, Observer<String> {
            it?.let {
//                com.docsink.patient.utill.showDialog(this@ForgotPasswordActivity,getString(R.string.docsink_patient), parseError(it))
            }
        })
        viewModel.apiResponse.observe(this, Observer<Any> {
            it?.let {
                if(it is BaseModel) {
                    if (it.error.isEmpty()) {
//                        com.docsink.patient.utill.showDialog(this@ForgotPasswordActivity, getString(R.string.docsink_patient), parseError(it.message))
                    } else {
//                        com.docsink.patient.utill.showDialog(this@ForgotPasswordActivity, getString(R.string.docsink_patient), parseError(it.message))
                    }
                }
            }
        })
    }

    private fun showLoadingDialog(show: Boolean) {
//        if (show) binding.progressBar.visibility = View.VISIBLE else binding.progressBar.visibility = View.GONE
//        if(show) binding.resetPassword.setText(getString(R.string.loading)) else  binding.resetPassword.setText(R.string.reset_password)
    }

  }
