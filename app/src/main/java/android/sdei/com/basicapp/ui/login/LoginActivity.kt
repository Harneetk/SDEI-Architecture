package android.sdei.com.basicapp.ui.login

import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.sdei.com.basicapp.Dashboard
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.databinding.ActivityLoginBinding
import android.sdei.com.basicapp.model.LoginResponse
import android.sdei.com.basicapp.ui.forgotpassword.ForgotPasswordActivity
import android.sdei.com.basicapp.ui.register.RegisterActivity
import android.sdei.com.basicapp.utill.PreferenceConnector
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast


class LoginActivity : AppCompatActivity()
{
    lateinit var binding: ActivityLoginBinding
    //private val registry : LifecycleRegistry by lazy { LifecycleRegistry(this)}
    private lateinit var viewModel: LoginViewModel
   // override fun getLifecycle(): LifecycleRegistry = registry

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = LoginViewModel()
        viewModel.isLoading.postValue(false)
        binding.viewModel = viewModel
        attachObserver()
        binding.etPasswordLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (s.length > 0) {
                    binding.etPasswordLayout.setError(null)
                    binding.etPasswordLayout.setErrorEnabled(false)
                }
            }
            override fun afterTextChanged(s: Editable) {}
        })



        binding.emailLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    binding.emailLayout.setError(null)
                    binding.emailLayout.setErrorEnabled(false)
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })


        binding.forgotPassword.setOnClickListener{

            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.bottomLayout.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
      
    }

    private fun attachObserver() {
        viewModel.isLoading.observe(this, Observer<Boolean> { it ->
            it?.let {
                showLoadingDialog(it)
            }
        })
        viewModel.apiError.observe(this, Observer<String> { it ->
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//                com.docsink.patient.utill.showDialog(this@LoginActivity,getString(R.string.docsink_patient),parseError(it))
            }
        })
        viewModel.apiResponse.observe(this, Observer<Any> { it ->
            it?.let {
                if(it is LoginResponse)
                {
                   if (it.status.equals("success"))
                   {
                        val isLogged =viewModel.isRemember.get()as Boolean
                        PreferenceConnector.writeBoolean(this, PreferenceConnector.isRemember,isLogged )
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        finish()

                    }

                   else {
                       Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                   }
                }

            }
        })
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE else binding.progressBar.visibility = View.GONE
        if(show) binding.signIn.setText(getString(R.string.loading)) else  binding.signIn.setText(R.string.sign_in)
    }


}

