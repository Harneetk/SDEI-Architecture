package com.sdei.sdeiarchitecture.repository.dagger.module

import android.content.Context
import com.sdei.sdeiarchitecture.repository.dagger.scope.AppScope
import com.sdei.sdeiarchitecture.repository.networkoperator.NetworkAdapter
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