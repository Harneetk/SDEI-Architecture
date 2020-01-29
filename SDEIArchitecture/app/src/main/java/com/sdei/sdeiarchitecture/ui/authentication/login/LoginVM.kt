package com.sdei.sdeiarchitecture.ui.authentication.login

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.sdei.sdeiarchitecture.ui.home.HomeActivity
import com.sdei.sdeiarchitecture.utils.base.BaseVM

/**
 * Created by Vishal Sharma on 2019-12-06.
 */
class LoginVM(application: Application) : BaseVM(application) {
    val emailObservable = ObservableField<String>()
    val passwordObservable = ObservableField<String>()
    val progressObservable = ObservableField<Boolean>()

    var errorResponse: MutableLiveData<String>? = null
    var progressObserver: MutableLiveData<Boolean>? = null


    var app: Application? = null

    init {
        app = application
        progressObservable.set(true)
    }

    fun observerError(): MutableLiveData<String>? {
        errorResponse = null
        errorResponse = MutableLiveData()
        return errorResponse
    }

    fun observerProgress(): MutableLiveData<Boolean>? {
        progressObserver = null
        progressObserver = MutableLiveData()
        return progressObserver
    }

    fun doLoginProcess() {
        Log.e("doLoginProcess", "dfsakdjgfaksdjkfasd")
        var email = ""
        var password = ""
        emailObservable.get()?.let {
            email = it
        }

        passwordObservable.get()?.let {
            password = it
        }

        when (email.isNotEmpty() && password.isNotEmpty()) {
            true -> {
                val intent = Intent(
                    app?.applicationContext,
                    HomeActivity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                app?.applicationContext?.startActivity(
                    intent
                )
            }
            else -> {
                when {
                    email.isEmpty() -> errorResponse?.value = "Email field cannot be empty"
                    password.isEmpty() -> errorResponse?.value = "Password field cannot be empty"
                }
            }
        }
        progressObserver?.value = true
    }
}


/*private var password: String? = null

@Bindable
fun getPassword(): String? {
    return password
}

fun setPassword(password: String) {
    this.password = password
    notifyPropertyChanged(BR.password)
}*/
