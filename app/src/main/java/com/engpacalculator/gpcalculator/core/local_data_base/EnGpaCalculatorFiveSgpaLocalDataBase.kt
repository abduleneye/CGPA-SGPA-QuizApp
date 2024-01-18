package com.engpacalculator.gpcalculator.core.local_data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.FiveCgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity

@Database(
    entities = [
        FiveSgpaResultEntity::class,
        FiveCgpaResultEntity::class


    ],
    version = 1
)
@TypeConverters(FiveSgpaDBFieldConverter::class)
abstract class EnGpaCalculatorLocalDataBase : RoomDatabase() {

    abstract val dao: FiveSgpaResultRecordDao
}

@TypeConverters(FiveCgpaDBFieldConverter::class)
abstract class EnGpaCalculatorLocalDataBase : RoomDatabase() {

    abstract val dao: FiveSgpaResultRecordDao
}
