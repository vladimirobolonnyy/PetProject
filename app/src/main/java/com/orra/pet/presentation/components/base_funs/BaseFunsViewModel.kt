package com.orra.pet.presentation.components.base_funs

import com.orra.pet.core_presentation.base.BaseViewModel
import com.orra.pet.core_presentation.base.SimpleErrorAction
import kotlinx.coroutines.delay

class BaseFunsViewModel() : BaseViewModel<Nothing>() {

    fun showStaticError() {
        addErrorAction(SimpleErrorAction)
    }

    fun hideStaticError() {
        mutableErrorState.value = emptySet()
    }

    fun showError() {
        showErrorMessage("error")
    }

    fun showSuccess() {
        showSuccessMessage("success")
    }

    fun showInfo() {
        showInfoMessage("info")
    }

    fun showAndHideLoading() {
        launchIO {
            toggleLoader(true)
            delay(1000)
            toggleLoader(false)
        }
    }
}