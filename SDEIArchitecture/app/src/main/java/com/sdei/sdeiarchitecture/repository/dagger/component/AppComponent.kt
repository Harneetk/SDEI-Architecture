package com.sdei.sdeiarchitecture.repository.dagger.component

import com.sdei.sdeiarchitecture.repository.dagger.module.AppHelperModule
import com.sdei.sdeiarchitecture.repository.dagger.module.NetworkAdapterHelperModule
import com.sdei.sdeiarchitecture.repository.dagger.module.SharedPreferenceModule
import com.sdei.sdeiarchitecture.repository.dagger.scope.AppScope
import com.sdei.sdeiarchitecture.utils.base.AppApplication
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.utils.base.BaseApplication
import dagger.Component

@AppScope
@Component(modules = [AppHelperModule::class, SharedPreferenceModule::class, NetworkAdapterHelperModule::class])
interface AppComponent {
    fun inject(app: BaseActivity)
}
