package com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FourSgpaResultRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFourSgpaResult(resultDetails: FourSgpaResultEntity)

    //@Query("DELETE FROM uniFourSgpaResultEntity WHERE resultName IN(:resultNames)")
    @Delete
    suspend fun DeleteFourCgpaResult(result: FourSgpaResultEntity)

    @Query("DELETE FROM foursgparesultentity WHERE resultName  = :resultToBeDeleted")
    suspend fun FourSgpaResultToBeDeleted(resultToBeDeleted: String)

    //@Query("SELECT * FROM uniFourSgpaResultEntity WHERE resultName LIKE '%' || :resultName  || '%' ")
    @Query("SELECT  * FROM foursgparesultentity")
    fun GetFullFourSgpaResults(
        //resultName: String
    ): Flow<List<FourSgpaResultEntity>>

//    @Query("SELECT resultName, gp, remark FROM uniFourSgpaResultEntity ")
//    fun getIntroResults(): Flow<List<UniFourSgpaResultIntroEntity>>
}
