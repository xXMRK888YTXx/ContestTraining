package com.xxmrk888ytxx.contesttraining.DI

import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.RefinancingRateRepository
import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.RefinancingRateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindsRefinancingRateRepository(
        refinancingRateRepository:RefinancingRateRepositoryImpl
    ) : RefinancingRateRepository
}