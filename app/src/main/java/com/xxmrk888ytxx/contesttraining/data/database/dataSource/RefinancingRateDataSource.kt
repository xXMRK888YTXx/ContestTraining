package com.xxmrk888ytxx.contesttraining.data.database.dataSource

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.models.RefinancingRateLocalModel
import com.xxmrk888ytxx.contesttraining.data.database.entityes.RefinancingRateEntity
import kotlinx.coroutines.flow.Flow

interface RefinancingRateDataSource {

    suspend fun getRateByDate(date:String) : RefinancingRateLocalModel?

    val rates: Flow<List<RefinancingRateLocalModel>>

    suspend fun insert(refinancingRateEntity: RefinancingRateLocalModel)
}