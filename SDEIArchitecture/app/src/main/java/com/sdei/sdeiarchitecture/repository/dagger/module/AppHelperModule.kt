package com.sdei.sdeiarchitecture.repository.dagger.module

import com.sdei.sdeiarchitecture.repository.dagger.AppHelper
import com.sdei.sdeiarchitecture.repository.dagger.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppHelperModule {

    @Provides @AppScope
    fun provideAppHelper(): AppHelper {
        return AppHelper()
    }

}