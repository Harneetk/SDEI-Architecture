package com.sdei.sdeiarchitecture.repository.dagger.component

import com.sdei.sdeiarchitecture.repository.dagger.module.NetworkAdapterHelperModule
import com.sdei.sdeiarchitecture.repository.dagger.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [NetworkAdapterHelperModule::class])
interface FragmentComponent {

//    fun inject(app: LoginFragment)

}