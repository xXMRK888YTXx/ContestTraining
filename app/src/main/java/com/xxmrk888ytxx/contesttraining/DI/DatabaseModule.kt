package com.xxmrk888ytxx.contesttraining.DI

import android.content.Context
import androidx.room.Room
import com.xxmrk888ytxx.contesttraining.data.database.AppDatabase
import com.xxmrk888ytxx.contesttraining.data.database.dao.RefinancingRateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context,AppDatabase::class.java,"database.db").build()
    }

    @Provides
    @Singleton
    fun provideRefinRateDao(appDatabase: AppDatabase) : RefinancingRateDao {
        return appDatabase.refinancingRateDao
    }

}