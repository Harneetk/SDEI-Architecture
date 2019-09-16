package com.sdei.sdeiarchitecture.helper.dagger.component

import com.sdei.sdeiarchitecture.helper.dagger.module.NetworkAdapterHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [NetworkAdapterHelperModule::class])
interface FragmentComponent {

//    fun inject(app: LoginFragment)

}