package com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.domain.repository

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import kotlinx.coroutines.flow.Flow

interface FourSgpaResultRepository {

    suspend fun InsertFourSgpaResult(result: FourSgpaResultEntity)


    suspend fun DeleteFourSgpaResult(result: FourSgpaResultEntity)

    suspend fun FourSgpaResultToBeDeleted(resultToBeDeleted: String)


    fun GetFourSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<FourSgpaResultEntity>>

//    fun getUniFourSgpaIntroResultRecordDao(
//        //resultName: String
//    ): Flow<List<UniFourSgpaResultIntroEntity>>


}