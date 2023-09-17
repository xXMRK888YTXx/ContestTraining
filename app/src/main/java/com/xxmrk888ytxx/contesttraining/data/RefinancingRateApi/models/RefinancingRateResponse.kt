package com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefinancingRateResponse(
    @SerialName("Date") val data:String,
    @SerialName("Value") val value:Float
)
