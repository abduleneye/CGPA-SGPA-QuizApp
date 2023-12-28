package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultEntity

@Database(
    entities = [
        UniFiveSgpaResultEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class UniFiveSgpaResultRecordDB : RoomDatabase() {

    abstract val dao: UniFiveSgpaResultRecordDao
}
