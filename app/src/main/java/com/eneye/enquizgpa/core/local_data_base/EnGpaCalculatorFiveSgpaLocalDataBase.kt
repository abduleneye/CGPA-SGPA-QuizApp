package com.eneye.enquizgpa.core.local_data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eneye.enquizgpa.features.Four_grading_system_sgpa_features.data.local.FourSgpaResultRecordDao
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.FiveCgpaDBFieldConverter
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.FiveCgpaResultRecordDao
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local.FiveSgpaDBFieldConverter
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local.FiveSgpaResultRecordDao
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local.FourSgpaDBFieldConverter
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local.FourCgpaResultRecordDao
import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity

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


