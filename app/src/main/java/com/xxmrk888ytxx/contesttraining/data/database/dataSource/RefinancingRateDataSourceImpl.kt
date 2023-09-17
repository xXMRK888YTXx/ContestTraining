package com.xxmrk888ytxx.contesttraining.data.database.dataSource

import com.xxmrk888ytxx.contesttraining.data.database.dao.RefinancingRateDao
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.models.RefinancingRateLocalModel
import com.xxmrk888ytxx.contesttraining.data.database.entityes.RefinancingRateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RefinancingRateDataSourceImpl @Inject constructor(
    private val refinancingRateDao: RefinancingRateDao
) : RefinancingRateDataSource {

    override suspend fun getRateByDate(date: String): RefinancingRateLocalModel? {
        return refinancingRateDao.getRateByDate(date)?.toModel()
    }

    override val rates: Flow<List<RefinancingRateLocalModel>>  = refinancingRateDao.rates.map { list ->
        list.map { it.toModel() }
    }

    override suspend fun insert(refinancingRateEntity: RefinancingRateLocalModel) {
        refinancingRateDao.insert(refinancingRateEntity.toEntity())
    }

    private fun RefinancingRateEntity.toModel() : RefinancingRateLocalModel {
        return RefinancingRateLocalModel(this.date,this.rate)
    }

    private fun RefinancingRateLocalModel.toEntity() : RefinancingRateEntity {
        return RefinancingRateEntity(
            date = this.date,
            rate = this.value
        )
    }
}