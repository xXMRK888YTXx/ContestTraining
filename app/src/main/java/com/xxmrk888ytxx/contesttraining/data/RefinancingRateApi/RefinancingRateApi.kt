package com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi

import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.models.RefinancingRateRemoteModel

interface RefinancingRateApi {

    suspend fun getRefinancingRate(
        year:Int,
        month:Int,
        day:Int
    ) : RefinancingRateRemoteModel
}