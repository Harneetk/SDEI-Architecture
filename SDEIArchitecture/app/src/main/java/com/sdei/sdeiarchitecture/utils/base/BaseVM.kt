package com.sdei.sdeiarchitecture.utils.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseVM(context: Application) : AndroidViewModel(context) {

    fun demoTest(): String {
        return "Hello Test"
    }
}