package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.repository

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.FiveSgpaResultRepository
import kotlinx.coroutines.flow.Flow

class FiveSgpaResultRepositoryImplementation(
    private val dao: FiveSgpaResultRecordDao
) : FiveSgpaResultRepository {
    override suspend fun InsertFiveSgpaResult(result: FiveSgpaResultEntity) {
        dao.insertFiveSgpaResult(result)
    }

    override fun GetFiveSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<FiveSgpaResultEntity>> {
        return dao.GetFullFiveSgpaResults()
    }
//
//    override fun getUniFiveSgpaIntroResultRecordDao(): Flow<List<UniFiveSgpaResultIntroEntity>> {
//        return dao.getIntroResults()
//    }


    override suspend fun DeleteFiveSgpaResult(result: FiveSgpaResultEntity) {
        dao.DeleteFiveCgpaResult(result)
    }

    override suspend fun FiveSgpaResultToBeDeleted(resultToBeDeleted: String) {
        dao.FiveSgpaResultToBeDeleted(resultToBeDeleted)
    }
}