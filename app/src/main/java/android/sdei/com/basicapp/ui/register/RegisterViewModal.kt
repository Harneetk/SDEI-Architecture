package android.sdei.com.basicapp.ui.register

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.sdei.com.basicapp.R
import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.repository.ApiUtilis
import android.sdei.com.basicapp.repository.ErrorModel
import android.sdei.com.basicapp.repository.NetworkManager
import android.sdei.com.basicapp.repository.ServiceListener
import android.sdei.com.basicapp.utill.isPasswordValid
import android.view.View
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Created by parmil.sharma on 16/05/18.
 */
class RegisterViewModal : ViewModel(){
    var apiResponse = MutableLiveData<BaseModel>()
    var isLoading = MutableLiveData<Boolean>()
    var apiError = MutableLiveData<String>()
    var termCondition = MutableLiveData<Boolean>()
    var email= ObservableField<String>()
    var password = ObservableField<String>()
    var verifyPassword = ObservableField<String>()
    var firstName= ObservableField<String>()
    var lastName = ObservableField<String>()

    var isTermCondition: ObservableField<Boolean> = ObservableField()
    var emailError= ObservableField<String>()
    var passwordError = ObservableField<String>()
    var firstNameError= ObservableField<String>()
    var lastNameError = ObservableField<String>()
    var errorVerifyPassword = ObservableField<String>()

    init {
        firstName.set("")
        lastName.set("")
        email.set("")
        password.set("")
        verifyPassword.set("")
        isTermCondition.set(true)
        setError()
     }

    fun setError(){
        firstNameError.set("")
        lastNameError.set("")
        emailError.set("")
        passwordError.set("")
        errorVerifyPassword.set("")
    }


    fun onRegisterClick(view: View){
        if(checkValidation(view)) {
            registerPatient();
        }
    }

    fun registerPatient(){
        if(isLoading.value==false)
        {
            isLoading.value = true
            val manager = NetworkManager()


            manager.createApiRequest(ApiUtilis.getAPIService().signUP(getrequestBody(firstName.get()), getrequestBody(lastName .get()) , getrequestBody(email .get()),getrequestBody(password .get())) ,object : ServiceListener<BaseModel> {
                override fun getServerResponse(response: BaseModel, requestcode: Int) {
                    apiResponse.value = response
                    isLoading.value = false
                }

                override fun getError(error: ErrorModel, requestcode: Int) {
                    apiError.value = error.error_message
                    isLoading.value = false
                }
            })
        }
    }

    internal fun getrequestBody(request: String? ): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), request)
    }


    private fun checkValidation(view: View) : Boolean{
        setError()
        var isValid =true
        if(firstName.get()!!.isEmpty()){
            firstNameError.set(view.context.getString(R.string.please_enter_first_name))
            isValid=false
        }

        else if(lastName.get()!!.isEmpty()){
            lastNameError.set(view.context.getString(R.string.please_enter_last_name))
            isValid=false
        }

        else if(email.get()!!.isEmpty()){
            emailError.set(view.context.getString(R.string.please_enter_email))
            isValid=false
        }
        else if(password.get()!!.isEmpty()){
            passwordError.set(view.context.getString(R.string.please_enter_password))
            isValid=false
        }
        else if(!isPasswordValid(password.get()!!)){
            passwordError.set("Password should contain minimun 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character.")
            isValid=false
        }


        else if(verifyPassword.get()!!.isEmpty()){
            errorVerifyPassword.set(view.context.getString(R.string.please_enter_password))
            isValid=false
        }
        else if(!password.get().equals(verifyPassword.get())){
            errorVerifyPassword.set(view.context.getString(R.string.password_not_match))
            isValid=false
        }

        else if(!isTermCondition.get()!!){
            isValid=false
            termCondition.value=false
        }
        return isValid
    }

}