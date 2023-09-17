package com.xxmrk888ytxx.contesttraining.presentation.Screens.RestScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.RefinancingRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestViewModel @Inject constructor(
    private val refinancingRateRepository: RefinancingRateRepository,
) : ViewModel() {

    val rates = refinancingRateRepository.rates

    val lastRate = refinancingRateRepository.lastRate

    val isError = MutableStateFlow(false)

    fun requestNewData() {
        viewModelScope.launch(Dispatchers.IO) {
            isError.update { false }

            refinancingRateRepository.requestTodayRate()
                .onSuccess { }
                .onFailure {
                    isError.update { true }
                }
        }
    }

    init {
        requestNewData()
    }
}