package com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository

import kotlinx.coroutines.flow.Flow

interface RefinancingRateRepository {

    suspend fun requestTodayRate() : Result<Unit>

    val rates: Flow<List<RefinancingRateModel>>

    val lastRate:Flow<RefinancingRateModel?>
}