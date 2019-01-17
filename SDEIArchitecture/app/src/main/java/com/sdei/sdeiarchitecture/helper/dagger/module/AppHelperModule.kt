package com.sdei.sdeiarchitecture.helper.dagger.module

import com.sdei.sdeiarchitecture.helper.dagger.AppHelper
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppHelperModule {

    @Provides @AppScope
    fun provideAppHelper(): AppHelper {
        return AppHelper()
    }

}