package com.xxmrk888ytxx.contesttraining.data.database.entityes

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("date", unique = true)]
)
data class RefinancingRateEntity(
    @PrimaryKey val date:String,
    val rate:Float,
)
