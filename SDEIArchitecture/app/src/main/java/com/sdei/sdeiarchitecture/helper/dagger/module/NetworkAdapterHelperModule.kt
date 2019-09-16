package com.sdei.sdeiarchitecture.helper.dagger.module

import android.content.Context
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import com.sdei.sdeiarchitecture.repository.networkOperator.NetworkAdapter
import dagger.Module
import dagger.Provides

@Module
class NetworkAdapterHelperModule(var context: Context) {
    @Provides
    @AppScope
    fun provideNetworkHelper(): NetworkAdapter {
        return NetworkAdapter.getInstance()
    }
}