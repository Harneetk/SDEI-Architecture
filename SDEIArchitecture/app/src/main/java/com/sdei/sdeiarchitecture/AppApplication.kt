package com.sdei.sdeiarchitecture

import android.app.Application
import com.sdei.sdeiarchitecture.helper.dagger.component.AppComponent
import com.sdei.sdeiarchitecture.helper.dagger.component.DaggerAppComponent
import com.sdei.sdeiarchitecture.helper.dagger.module.ApiHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.module.AppHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.module.SharedPreferenceModule

class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        val component = initDaggerComponent()
        this.appComponent = component
    }

    private fun initDaggerComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appHelperModule(AppHelperModule())
            .sharedPreferenceModule(SharedPreferenceModule(applicationContext))
            .apiHelperModule(ApiHelperModule(applicationContext))
            .build()
    }

}