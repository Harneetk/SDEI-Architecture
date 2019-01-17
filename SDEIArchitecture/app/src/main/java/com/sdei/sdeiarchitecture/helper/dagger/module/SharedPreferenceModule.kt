package com.sdei.sdeiarchitecture.helper.dagger.module

import android.content.Context
import com.sdei.sdeiarchitecture.BuildConfig
import com.sdei.sdeiarchitecture.helper.dagger.SharedPreferenceHelper
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class SharedPreferenceModule(var context: Context) {

    @Provides @AppScope
    fun provideSharedPreference(): SharedPreferenceHelper {
        val preference = context.getSharedPreferences(BuildConfig.APP_PREF, Context.MODE_PRIVATE)
        return SharedPreferenceHelper(preference)
    }

}