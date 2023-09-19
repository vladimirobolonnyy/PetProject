package com.orra.pet.presentation.components.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orra.pet.data.dto.AmericaHoliday
import com.orra.pet.data.repository.DataTestRepositoryImpl
import com.orra.pet.domain.repositoty.DataTestRepository
import com.orra.pet.core_presentation.utils.asResult
import com.orra.pet.core_presentation.base.BaseViewModel

class RequestViewModel(
    private val repository: DataTestRepository = DataTestRepositoryImpl()
) : BaseViewModel<Nothing>() {

    private val _viewState = MutableLiveData<List<AmericaHoliday>?>()
    val viewState: LiveData<List<AmericaHoliday>?> = _viewState

    init {
        loadData()
    }

    private fun loadData() {
        launchWithProgress {
            asResult {
                repository.getTestData()
            }.onSuccessUI {
                _viewState.value = it
            }.onFailureUI {
                println(it)
                showErrorMessage()
            }
        }
    }

    fun reload() {
        _viewState.value = null
        loadData()
    }
}