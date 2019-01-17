package com.sdei.sdeiarchitecture.helper.dagger.module

import android.content.Context
import com.sdei.sdeiarchitecture.helper.dagger.ApiHelper
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import com.sdei.sdeiarchitecture.helper.dagger.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class ApiHelperModule(var context: Context) {

    @Provides @AppScope
    fun provideApiHelper(): ApiHelper {
        return ApiHelper(context)
    }

}