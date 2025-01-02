package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.repository

import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local.FourCgpaResultRecordDao
import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity
import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.domain.repository.FourCgpaResultRepository
import kotlinx.coroutines.flow.Flow

class FourCgpaResultRepositoryImplementation(
    private val dao: FourCgpaResultRecordDao,

    ) : FourCgpaResultRepository {
    override suspend fun InsertFourCgpaResult(result: FourCgpaResultEntity) {
        dao.insertFourCgpaResult(result)
    }

    override suspend fun DeleteFourCgpaResult(result: FourCgpaResultEntity) {
        dao.DeleteFourCgpaResult(result)
    }

    override suspend fun FourCgpaResultToBeDeleted(resultToBeDeleted: String) {
        dao.FourCgpaResultToBeDeleted(resultToBeDeleted)
    }

    override fun GetFourCgpaResultRecordDao(): Flow<List<FourCgpaResultEntity>> {
        return dao.getFullFourCgpaResults()
    }
}