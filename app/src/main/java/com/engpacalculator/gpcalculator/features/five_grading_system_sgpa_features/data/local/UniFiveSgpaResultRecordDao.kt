package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UniFiveSgpaResultRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniFiveSgpaResult(resultDetails: UniFiveSgpaResultEntity)

    //@Query("DELETE FROM unifivesgparesultentity WHERE resultName IN(:resultNames)")
    @Delete
    suspend fun deleteResult(result: UniFiveSgpaResultEntity)


    //@Query("SELECT * FROM unifivesgparesultentity WHERE resultName LIKE '%' || :resultName  || '%' ")
    @Query("SELECT  * FROM unifivesgparesultentity")
    fun getResults(
        //resultName: String
    ): Flow<List<UniFiveSgpaResultEntity>>
}
