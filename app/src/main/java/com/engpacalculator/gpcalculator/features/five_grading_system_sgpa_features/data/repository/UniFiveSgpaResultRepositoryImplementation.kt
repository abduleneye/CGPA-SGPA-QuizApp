package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.repository

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.UniFiveSgpaResultRecordDao
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.UniFiveSgpaResultRepository
import kotlinx.coroutines.flow.Flow

class UniFiveSgpaResultRepositoryImplementation(
    private val dao: UniFiveSgpaResultRecordDao
) : UniFiveSgpaResultRepository {
    override suspend fun insertResult(result: UniFiveSgpaResultEntity) {
        dao.insertUniFiveSgpaResult(result)
    }

    override fun getUniFiveSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<UniFiveSgpaResultEntity>> {
        return dao.getResults()
    }


    override suspend fun deleteResult(result: UniFiveSgpaResultEntity) {
        dao.deleteResult(result)
    }
}