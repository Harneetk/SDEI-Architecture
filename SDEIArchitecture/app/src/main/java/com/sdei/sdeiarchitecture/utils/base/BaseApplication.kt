package com.sdei.sdeiarchitecture.utils.base

import android.app.Application
import android.content.Context


open class BaseApplication : Application() {

    companion object {
        private var appInstance: BaseApplication? = null

        fun getAppInstance(): BaseApplication {
            if (appInstance == null) {
                return BaseApplication()
            }
            return appInstance as BaseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        //(applicationContext as AppApplication).appComponent.inject(this)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

}