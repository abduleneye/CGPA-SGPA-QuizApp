package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity

import GpCalculatorPrototype.Data.FourGpData
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.model.FourSgpaResult
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity
@Parcelize
data class FourSgpaResultEntity(

    val resultEntries: @RawValue ArrayList<FourGpData> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
    val resultGpaDescriptor: String = "",
    @PrimaryKey val id: Int? = null
)

//Mapper
    : Parcelable {
    //Mapper Function
    fun toUniFiveSgpaResult(): FourSgpaResult {
        return FourSgpaResult(
            // resultEntries = resultEntries,
            remark = remark,
            gp = gp,
            resultEntries = resultEntries,
            resultName = resultName


        )
    }
}

//data class UniFiveSgpaResultIntroEntity(
//    val gp: String = "",
//    val remark: String = "",
//    val resultName: String = "",
//)