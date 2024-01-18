package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity

import GpCalculatorPrototype.Data.GpData
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.model.UniFiveSgpaResult
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UniFiveSgpaResultEntity(

    val resultEntries: ArrayList<GpData> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
    @PrimaryKey val id: Int? = null
)

//Mapper
    : Parcelable {
    //Mapper Function
    fun toUniFiveSgpaResult(): UniFiveSgpaResult {
        return UniFiveSgpaResult(
            // resultEntries = resultEntries,
            remark = remark,
            gp = gp,
            resultEntries = resultEntries,
            resultName = resultName


        )
    }
}