package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultIntroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UniFiveSgpaResultRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUniFiveSgpaResult(resultDetails: UniFiveSgpaResultEntity)

    //@Query("DELETE FROM unifivesgparesultentity WHERE resultName IN(:resultNames)")
    @Delete
    suspend fun deleteResult(result: UniFiveSgpaResultEntity)

    @Query("DELETE FROM unifivesgparesultentity WHERE resultName  = :resultToBeDeleted")
    suspend fun resultToBeDeleted(resultToBeDeleted: String)

    //@Query("SELECT * FROM unifivesgparesultentity WHERE resultName LIKE '%' || :resultName  || '%' ")
    @Query("SELECT  * FROM unifivesgparesultentity")
    fun getFullResults(
        //resultName: String
    ): Flow<List<UniFiveSgpaResultEntity>>

    @Query("SELECT resultName, gp, remark FROM unifivesgparesultentity ")
    fun getIntroResults(): Flow<List<UniFiveSgpaResultIntroEntity>>
}
