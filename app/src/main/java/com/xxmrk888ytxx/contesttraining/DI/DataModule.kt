package com.xxmrk888ytxx.contesttraining.DI

import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.RefinancingRateApi
import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.RefinancingRateApiImpl
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.RefinancingRateDataSource
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.RefinancingRateDataSourceImpl
import com.xxmrk888ytxx.contesttraining.data.database.dataSource.models.RefinancingRateLocalModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideRefinancingRateApi(
        refinancingRateApiImpl: RefinancingRateApiImpl
    ) : RefinancingRateApi

    @Binds
    fun bindRefinancingRateDataSource(
        refinancingRateLocalDataSource: RefinancingRateDataSourceImpl
    )  : RefinancingRateDataSource
}