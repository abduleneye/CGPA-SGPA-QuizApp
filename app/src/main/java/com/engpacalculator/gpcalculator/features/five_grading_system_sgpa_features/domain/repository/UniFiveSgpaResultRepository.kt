package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository

import kotlinx.coroutines.flow.Flow

interface UniFiveSgpaResultRepository {

    suspend fun insertResult(result: UniFiveSgpaResultEntity)
    fun getUniFiveSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<UniFiveSgpaResultEntity>>

    suspend fun deleteResult(result: UniFiveSgpaResultEntity)


}