package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.domain.repository

import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity
import kotlinx.coroutines.flow.Flow

interface FourCgpaResultRepository {
    suspend fun InsertFourCgpaResult(result: FourCgpaResultEntity)


    suspend fun DeleteFourCgpaResult(result: FourCgpaResultEntity)

    suspend fun FourCgpaResultToBeDeleted(resultToBeDeleted: String)


    fun GetFourCgpaResultRecordDao(
        //resultName: String
    ): Flow<List<FourCgpaResultEntity>>

}