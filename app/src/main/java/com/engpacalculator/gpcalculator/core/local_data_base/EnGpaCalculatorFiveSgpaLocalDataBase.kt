package com.engpacalculator.gpcalculator.core.local_data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.data.local.FourSgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.FiveCgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.FiveCgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FourSgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local.FourCgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity

@Database(
    entities = [
        FiveSgpaResultEntity::class,
        FiveCgpaResultEntity::class,
        FourSgpaResultEntity::class,
        FourCgpaResultEntity::class


    ],
    version = 1
)
@TypeConverters(
    FiveSgpaDBFieldConverter::class,
    FiveCgpaDBFieldConverter::class,
    FourSgpaDBFieldConverter::class,
    // FiveCgpaDBFieldConverter::class
)

abstract class EnGpaCalculatorAndQuizLocalDataBase : RoomDatabase() {

    abstract val fiveSgpaDao: FiveSgpaResultRecordDao
    abstract val fiveCgpaDao: FiveCgpaResultRecordDao
    abstract val fourSgpaDao: FourSgpaResultRecordDao
    abstract val fourCgpaDao: FourCgpaResultRecordDao

}


