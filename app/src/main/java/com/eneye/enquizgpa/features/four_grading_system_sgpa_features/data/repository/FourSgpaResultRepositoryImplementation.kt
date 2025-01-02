package com.eneye.enquizgpa.features.Four_grading_system_sgpa_features.data.repository

import com.eneye.enquizgpa.features.Four_grading_system_sgpa_features.data.local.FourSgpaResultRecordDao
import com.eneye.enquizgpa.features.Four_grading_system_sgpa_features.domain.repository.FourSgpaResultRepository
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import kotlinx.coroutines.flow.Flow

class FourSgpaResultRepositoryImplementation(
    private val dao: FourSgpaResultRecordDao,
) : FourSgpaResultRepository {
    override suspend fun InsertFourSgpaResult(result: FourSgpaResultEntity) {
        dao.insertFourSgpaResult(result)
    }

    override fun GetFourSgpaResultRecordDao(
        //resultName: String
    ): Flow<List<FourSgpaResultEntity>> {
        return dao.GetFullFourSgpaResults()
    }
//
//    override fun getUniFourSgpaIntroResultRecordDao(): Flow<List<UniFourSgpaResultIntroEntity>> {
//        return dao.getIntroResults()
//    }


    override suspend fun DeleteFourSgpaResult(result: FourSgpaResultEntity) {
        dao.DeleteFourCgpaResult(result)
    }

    override suspend fun FourSgpaResultToBeDeleted(resultToBeDeleted: String) {
        dao.FourSgpaResultToBeDeleted(resultToBeDeleted)
    }
}