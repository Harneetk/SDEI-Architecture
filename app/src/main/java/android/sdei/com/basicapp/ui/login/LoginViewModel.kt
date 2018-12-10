package android.sdei.com.basicapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.model.LoginResponse
import android.sdei.com.basicapp.repository.ApiUtilis
import android.sdei.com.basicapp.repository.ErrorModel
import android.sdei.com.basicapp.repository.NetworkManager
import android.sdei.com.basicapp.repository.ServiceListener
import android.view.View

/**
 * Created by parmil.sharma on 14/02/18.
 */
class LoginViewModel : ViewModel()
{
    var isLoading = MutableLiveData<Boolean>()
    var apiError = MutableLiveData<String>()
    var apiResponse = MutableLiveData<Any>()
    var email = ObservableField<String>()
    var isRemember = ObservableField<Boolean>(false)
    var password = ObservableField<String>()
    var emailError = ObservableField<String>()
    var passwordError = ObservableField<String>()


    init {


       email.set("gs@g.com");
       password.set("12345678");

       emailError.set("")
       passwordError.set("")
    }

    fun onLoginClick(view: View) {
        emailError.set("")
        passwordError.set("")
        if (email.get()!!.isEmpty()) {
            emailError.set(view.context.getString(R.string.please_enter_email))
        } else if (password.get()!!.isEmpty()) {
            passwordError.set(view.context.getString(R.string.please_enter_password))
        } else {
            if(isLoading.value==false) {
                isLoading.value = true
                val manager = NetworkManager()
                manager.createApiRequest(ApiUtilis.getAPIService().login(email.get()!!, password.get()!!), object : ServiceListener<LoginResponse> {
                    override fun getServerResponse(response: LoginResponse, requestcode: Int) {

                        apiResponse.value = response

                        apiError.value = response.message
                        isLoading.value = false



                    }

                    override fun getError(error: ErrorModel, requestcode: Int) {
                        apiError.value = error.error_message
                        isLoading.value = false
                    }
                })
            }
        }
    }



    }



