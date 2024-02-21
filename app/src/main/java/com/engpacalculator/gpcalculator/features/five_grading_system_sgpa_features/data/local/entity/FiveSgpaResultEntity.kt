package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity

import GpCalculatorPrototype.Data.FiveGpData
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.model.FiveSgpaResult
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FiveSgpaResultEntity(

    val resultEntries: ArrayList<FiveGpData> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
    val resultGpaDescriptor: String = "",
    @PrimaryKey val id: Int? = null
)

//Mapper
    : Parcelable {
    //Mapper Function
    fun toUniFiveSgpaResult(): FiveSgpaResult {
        return FiveSgpaResult(
            // resultEntries = resultEntries,
            remark = remark,
            gp = gp,
            resultEntries = resultEntries,
            resultName = resultName,


            )
    }
}

//data class UniFiveSgpaResultIntroEntity(
//    val gp: String = "",
//    val remark: String = "",
//    val resultName: String = "",
//)