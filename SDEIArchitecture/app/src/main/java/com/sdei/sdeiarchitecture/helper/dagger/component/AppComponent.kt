package com.sdei.sdeiarchitecture.helper.dagger.component

import com.sdei.sdeiarchitecture.utils.common.BaseActivity
import com.sdei.sdeiarchitecture.helper.dagger.module.AppHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.module.NetworkAdapterHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.module.SharedPreferenceModule
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppHelperModule::class, SharedPreferenceModule::class, NetworkAdapterHelperModule::class])
interface AppComponent {
    fun inject(app: BaseActivity)
}
