package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FourCgpaResultRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFourCgpaResult(resultDetails: FourCgpaResultEntity)

    //@Query("DELETE FROM unifoursgparesultentity WHERE resultName IN(:resultNames)")
    @Delete
    suspend fun DeleteFourCgpaResult(result: FourCgpaResultEntity)

    @Query("DELETE FROM fourCgpaResultEntity WHERE resultName  = :resultToBeDeleted")
    suspend fun FourCgpaResultToBeDeleted(resultToBeDeleted: String)

    //@Query("SELECT * FROM unifoursgparesultentity WHERE resultName LIKE '%' || :resultName  || '%' ")
    @Query("SELECT  * FROM fourCgpaResultEntity")
    fun getFullFourCgpaResults(
        //resultName: String
    ): Flow<List<FourCgpaResultEntity>>

//    @Query("SELECT resultName, gp, remark FROM unifoursgparesultentity ")
//    fun getIntroResults(): Flow<List<UniFourSgpaResultIntroEntity>>
}