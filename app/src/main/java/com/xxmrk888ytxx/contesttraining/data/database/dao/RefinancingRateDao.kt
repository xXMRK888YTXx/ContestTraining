package com.xxmrk888ytxx.contesttraining.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xxmrk888ytxx.contesttraining.data.database.entityes.RefinancingRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RefinancingRateDao {

    @Query("SELECT * FROM RefinancingRateEntity WHERE date = :date LIMIT 1")
    suspend fun getRateByDate(date:String) : RefinancingRateEntity?

    @get:Query("SELECT * FROM REFINANCINGRATEENTITY")
    val rates:Flow<List<RefinancingRateEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(refinancingRateEntity: RefinancingRateEntity)
}