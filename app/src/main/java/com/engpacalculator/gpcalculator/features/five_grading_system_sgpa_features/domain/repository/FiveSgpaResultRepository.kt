package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity
import kotlinx.coroutines.flow.Flow

interface FiveSgpaResultRepository {

    suspend fun InsertFiveSgpaResult(result: FiveSgpaResultEntity)


    suspend fun DeleteFiveSgpaResult(result: FiveSgpaResultEntity)

    suspend fun FiveSgpaResultToBeDeleted(resultToBeDeleted: String)


    fun GetFiveSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<FiveSgpaResultEntity>>

//    fun getUniFiveSgpaIntroResultRecordDao(
//        //resultName: String
//    ): Flow<List<UniFiveSgpaResultIntroEntity>>


}