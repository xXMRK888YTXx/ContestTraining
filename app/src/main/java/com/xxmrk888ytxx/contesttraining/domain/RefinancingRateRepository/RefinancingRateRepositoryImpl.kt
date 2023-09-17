package com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository

import android.icu.util.Calendar
import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.RefinancingRateApi
import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.models.RefinancingRateRemoteModel
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.RefinancingRateDataSource
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.models.RefinancingRateLocalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RefinancingRateRepositoryImpl @Inject constructor(
    private val refinancingRateApi: RefinancingRateApi,
    private val refinancingRateDataSource: RefinancingRateDataSource
) : RefinancingRateRepository {

    override suspend fun requestTodayRate(): Result<Unit> {
        try {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dateString = "$year-$month-$day"



            if(refinancingRateDataSource.getRateByDate(dateString) == null) {
                val refinancingRate  = refinancingRateApi.getRefinancingRate(year, month, day)

                refinancingRateDataSource.insert(refinancingRate.toLocalModel())
            }

            return Result.success(Unit)
        }catch (e:Exception) {
            return Result.failure(e)
        }
    }

    override val rates: Flow<List<RefinancingRateModel>> = refinancingRateDataSource.rates.map { list ->
        list.map { it.toModel() }
    }
    override val lastRate: Flow<RefinancingRateModel?> = rates.map {
        it.lastOrNull()
    }

    private fun RefinancingRateLocalModel.toModel() : RefinancingRateModel {
        return RefinancingRateModel(this.date,this.value)
    }

    private fun RefinancingRateRemoteModel.toLocalModel() : RefinancingRateLocalModel {
        return RefinancingRateLocalModel(this.data,this.refinancingRate)
    }
}