package GpCalculatorPrototype.Data

import android.os.Parcelable

@kotlinx.parcelize.Parcelize

data class FiveGpData(

    val courseCode: String = "",
    val courseGrade: String,
    val courseUnit: Int

) : Parcelable

