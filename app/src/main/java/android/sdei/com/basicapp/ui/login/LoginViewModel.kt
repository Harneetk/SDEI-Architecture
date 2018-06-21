package android.sdei.com.basicapp.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.model.LoginModel
import android.sdei.com.basicapp.model.User
import android.sdei.com.basicapp.repository.ApiUtilis
import android.sdei.com.basicapp.repository.ErrorModel
import android.sdei.com.basicapp.repository.NetworkManager
import android.sdei.com.basicapp.repository.ServiceListener
import android.sdei.com.basicapp.utill.AppInstance
import android.sdei.com.basicapp.utill.DEVICE_TYPE
import android.util.Log
import android.view.View

/**
 * Created by parmil.sharma on 14/02/18.
 */
class LoginViewModel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var apiError = MutableLiveData<String>()
    var apiResponse = MutableLiveData<Any>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var emailError = ObservableField<String>()
    var passwordError = ObservableField<String>()

    init {
       //email.set("");
       //password.set("");

       email.set("parmil.sharma@smartdatainc.net");
       password.set("ABcd11!!");
      // email.set("amitk.shukla@smartdatainc.net");
      // password.set("ABcd11!!");

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
                manager.createApiRequest(ApiUtilis.getAPIService().login(email.get()!!, password.get()!!), object : ServiceListener<LoginModel> {
                    override fun getServerResponse(response: LoginModel, requestcode: Int) {
                        AppInstance.loginModel = response
                        getCurrentUser(AppInstance.loginModel!!.getToken())
                    }

                    override fun getError(error: ErrorModel, requestcode: Int) {
                        apiError.value = error.error_message
                        isLoading.value = false
                    }
                })
            }
        }
    }


    fun getCurrentUser(token: String){
        if(!token.isNullOrEmpty()) {
//            val manager = NetworkManager()
//            manager.createApiRequest(ApiUtilis.getAPIService().getCurrentUser(token!!), object : ServiceListener<User> {
//                override fun getServerResponse(response: User, requestcode: Int) {
//                    AppInstance.userModel = response
//                    apiResponse.value = response
//                    saveToken(token)
//                }
//                override fun getError(error: ErrorModel, requestcode: Int) {
//                    apiError.value = error.error_message
//                    isLoading.value = false
//                }
//            })
        }
    }


    fun saveToken(token: String){
//        val refreshedToken = FirebaseInstanceId.getInstance().token
//        if(!refreshedToken.isNullOrEmpty()) {
//            val manager = NetworkManager()
//            manager.createApiRequest(ApiUtilis.getAPIService().saveDeviceToken(token,refreshedToken!!,DEVICE_TYPE), object : ServiceListener<String> {
//                override fun getServerResponse(response: String, requestcode: Int) {
//                 Log.d("Response:::",""+response.toString())
//                }
//                override fun getError(error: ErrorModel, requestcode: Int) {
//                    Log.d("Response:::",""+error.toString())
//                }
//            })
//        }
    }
}