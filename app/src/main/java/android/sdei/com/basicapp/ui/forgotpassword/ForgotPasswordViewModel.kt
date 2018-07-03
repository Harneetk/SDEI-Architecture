package android.sdei.com.basicapp.ui.forgotpassword

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.bluetooth.BluetoothProfile
import android.databinding.ObservableField
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.repository.ApiUtilis
import android.sdei.com.basicapp.repository.ErrorModel
import android.sdei.com.basicapp.repository.NetworkManager
import android.sdei.com.basicapp.repository.ServiceListener
import android.view.View
/**
 * This  model class  contain all the fields of the forgot passpword api.
 */
class ForgotPasswordViewModel: ViewModel(){
    var isLoading = MutableLiveData<Boolean>()
    var apiError = MutableLiveData<String>()
    var apiResponse = MutableLiveData<Any>()
//    var email = ObservableField<String>()
//    var emailError = ObservableField<String>()
    var email = ObservableField<String>()
    var emailError = ObservableField<String>()


    init {
        email.set("")
        emailError.set("")
        isLoading.value=false
    }

//    fun onForgotPassword(view: View) {
//        emailError.set("")
//       if (email.get()!!.isEmpty()) {
//            emailError.set(view.context.getString(R.string.please_enter_email))
//        } else {
//            if(isLoading.value==false) {
//                isLoading.value = true
//                val manager = NetworkManager()
//                manager.createApiRequest(ApiUtilis.getAPIService().forgotPassword(email.get()!!), object : ServiceListener<BaseModel> {
//                    override fun getServerResponse(response: BaseModel, requestcode: Int) {
//                        apiResponse.value = response
//                        isLoading.value = false
//                    }
//
//                    override fun getError(error: ErrorModel, requestcode: Int) {
//                        apiError.value = error.error_message
//                        isLoading.value = false
//                    }
//                })
//            }
//        }
//    }
}