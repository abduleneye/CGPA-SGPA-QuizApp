package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultIntroEntity
import kotlinx.coroutines.flow.Flow

interface UniFiveSgpaResultRepository {

    suspend fun insertResult(result: UniFiveSgpaResultEntity)


    suspend fun deleteResult(result: UniFiveSgpaResultEntity)

    suspend fun resultToBeDeleted(resultToBeDeleted: String)


    fun getUniFiveSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<UniFiveSgpaResultEntity>>

    fun getUniFiveSgpaIntroResultRecordDao(
        //resultName: String
    ): Flow<List<UniFiveSgpaResultIntroEntity>>


}