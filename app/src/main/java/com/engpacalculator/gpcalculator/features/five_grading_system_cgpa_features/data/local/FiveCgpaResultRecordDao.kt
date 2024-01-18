package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiveCgpaResultRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFiveCgpaResult(resultDetails: FiveCgpaResultEntity)

    //@Query("DELETE FROM unifivesgparesultentity WHERE resultName IN(:resultNames)")
    @Delete
    suspend fun DeleteFiveCgpaResult(result: FiveCgpaResultEntity)

    @Query("DELETE FROM fivecgparesultentity WHERE resultName  = :resultToBeDeleted")
    suspend fun FiveCgpaResultToBeDeleted(resultToBeDeleted: String)

    //@Query("SELECT * FROM unifivesgparesultentity WHERE resultName LIKE '%' || :resultName  || '%' ")
    @Query("SELECT  * FROM fivecgparesultentity")
    fun getFullFiveCgpaResults(
        //resultName: String
    ): Flow<List<FiveCgpaResultEntity>>

//    @Query("SELECT resultName, gp, remark FROM unifivesgparesultentity ")
//    fun getIntroResults(): Flow<List<UniFiveSgpaResultIntroEntity>>
}