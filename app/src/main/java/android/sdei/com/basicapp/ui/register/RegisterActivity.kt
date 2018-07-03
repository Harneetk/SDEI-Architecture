package android.sdei.com.basicapp.ui.register

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sdei.com.basicapp.BaseActivity
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.databinding.ActivityRegisterBinding
import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.utill.parseError
import android.view.Menu
import android.view.MenuItem
import android.view.View

class RegisterActivity : BaseActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModal

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = RegisterViewModal();
        binding.viewModel = viewModel
        viewModel.isLoading.postValue(false)

        attachObserver()
    }

    private fun attachObserver()
    {
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        viewModel.termCondition.observe(this, Observer<Boolean> {
            it?.let {
                if (!it) {
//                com.docsink.patient.utill.showDialog(this@RegisterActivity,getString(R.string.docsink_patient),parseError("Please accept Terms of Service."))}
                }
            }
        })

        viewModel.apiError.observe(this, Observer<String> {
            it?.let {
//             com.docsink.patient.utill.showDialog(this@RegisterActivity,getString(R.string.docsink_patient), parseError(it))
            }
        })
        viewModel.apiResponse.observe(this, Observer<BaseModel> {
            it?.let {
                if(it.error.isEmpty()) {
                   finish()
                }
                else{
//                    com.docsink.patient.utill.showDialog(this@RegisterActivity,getString(R.string.docsink_patient),parseError(it.message))
                }
            }
        })
    }






    private fun showLoadingDialog(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE else binding.progressBar.visibility = View.GONE
    }
}
