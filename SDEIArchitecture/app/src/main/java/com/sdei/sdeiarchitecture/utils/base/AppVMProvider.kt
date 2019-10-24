package com.sdei.sdeiarchitecture.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 * ViewModel Provider class for sending parameters along to the ViewModel class ...
 *
 */
class AppVMProvider : ViewModelProvider.Factory {
    private var viewModelMap: Map<String, ViewModel>? = null

    fun createParams(viewModel: ViewModel) {
        viewModelMap = HashMap()
        (viewModelMap as HashMap<String, ViewModel>)[viewModel.javaClass.simpleName] = viewModel
        create(viewModel.javaClass)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        for (viewModel in viewModelMap?.keys!!) {
            if (viewModel == modelClass.simpleName) {
                return viewModelMap?.get(viewModel) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
