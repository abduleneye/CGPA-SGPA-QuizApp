package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiveSgpaResultRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFiveSgpaResult(resultDetails: FiveSgpaResultEntity)

    //@Query("DELETE FROM unifivesgparesultentity WHERE resultName IN(:resultNames)")
    @Delete
    suspend fun DeleteFiveCgpaResult(result: FiveSgpaResultEntity)

    @Query("DELETE FROM fivesgparesultentity WHERE resultName  = :resultToBeDeleted")
    suspend fun FiveSgpaResultToBeDeleted(resultToBeDeleted: String)

    //@Query("SELECT * FROM unifivesgparesultentity WHERE resultName LIKE '%' || :resultName  || '%' ")
    @Query("SELECT  * FROM fivesgparesultentity")
    fun GetFullFiveSgpaResults(
        //resultName: String
    ): Flow<List<FiveSgpaResultEntity>>

//    @Query("SELECT resultName, gp, remark FROM unifivesgparesultentity ")
//    fun getIntroResults(): Flow<List<UniFiveSgpaResultIntroEntity>>
}
