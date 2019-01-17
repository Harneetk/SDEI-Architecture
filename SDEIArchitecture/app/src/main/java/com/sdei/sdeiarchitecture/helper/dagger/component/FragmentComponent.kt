package com.sdei.sdeiarchitecture.helper.dagger.component

import com.sdei.sdeiarchitecture.helper.dagger.module.ApiHelperModule
import com.sdei.sdeiarchitecture.helper.dagger.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(ApiHelperModule::class))
interface FragmentComponent {

//    fun inject(app: LoginFragment)

}