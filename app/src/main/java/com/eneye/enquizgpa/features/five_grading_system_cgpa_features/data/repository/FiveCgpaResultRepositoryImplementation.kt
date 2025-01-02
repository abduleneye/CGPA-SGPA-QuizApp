package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.repository

import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.FiveCgpaResultRecordDao
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.domain.repository.FiveCgpaResultRepository
import kotlinx.coroutines.flow.Flow

class FiveCgpaResultRepositoryImplementation(
    private val dao: FiveCgpaResultRecordDao,

    ) : FiveCgpaResultRepository {
    override suspend fun InsertFiveCgpaResult(result: FiveCgpaResultEntity) {
        dao.insertFiveCgpaResult(result)
    }

    override suspend fun DeleteFiveCgpaResult(result: FiveCgpaResultEntity) {
        dao.DeleteFiveCgpaResult(result)
    }

    override suspend fun FiveCgpaResultToBeDeleted(resultToBeDeleted: String) {
        dao.FiveCgpaResultToBeDeleted(resultToBeDeleted)
    }

    override fun GetFiveCgpaResultRecordDao(): Flow<List<FiveCgpaResultEntity>> {
        return dao.getFullFiveCgpaResults()
    }
}