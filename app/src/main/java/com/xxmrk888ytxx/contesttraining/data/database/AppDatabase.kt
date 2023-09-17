package com.xxmrk888ytxx.contesttraining.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmrk888ytxx.contesttraining.data.database.dao.RefinancingRateDao
import com.xxmrk888ytxx.contesttraining.data.database.entityes.RefinancingRateEntity

@Database(
    version = 1,
    entities = [
        RefinancingRateEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val refinancingRateDao:RefinancingRateDao
}