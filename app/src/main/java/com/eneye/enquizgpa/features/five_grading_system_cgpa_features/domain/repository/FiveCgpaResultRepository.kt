package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.domain.repository

import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import kotlinx.coroutines.flow.Flow

interface FiveCgpaResultRepository {
    suspend fun InsertFiveCgpaResult(result: FiveCgpaResultEntity)


    suspend fun DeleteFiveCgpaResult(result: FiveCgpaResultEntity)

    suspend fun FiveCgpaResultToBeDeleted(resultToBeDeleted: String)


    fun GetFiveCgpaResultRecordDao(
        //resultName: String
    ): Flow<List<FiveCgpaResultEntity>>

}