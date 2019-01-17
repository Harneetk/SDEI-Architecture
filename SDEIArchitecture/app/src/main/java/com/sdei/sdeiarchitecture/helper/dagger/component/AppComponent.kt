package com.sdei.sdeiarchitecture.helper.dagger.component

import com.sdei.sdeiarchitecture.BaseActivity
import com.sdei.sdeiarchitecture.helper.dagger.module.ApiHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.module.AppHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.module.SharedPreferenceModule
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [AppHelperModule::class, SharedPreferenceModule::class, ApiHelperModule::class])
interface AppComponent {
    fun inject(app: BaseActivity)
}