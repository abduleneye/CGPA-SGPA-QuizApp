package GpCalculatorPrototype.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CourseDataEntries(

    val coursesDataEntry: @RawValue ArrayList<GpData> = ArrayList<GpData>(),
    var sixUnitCoursesPointSum: Int = 0,
    var fourUnitCoursesPointSum: Int = 0,
    var threeUnitCoursesPointSum: Int = 0,
    var twoUnitCoursesPointSum: Int = 0,
    var oneUnitCoursesPointSum: Int = 0,
    var totalCoursesPointSum: Double = 0.0,

    var gradeList: List<String> = listOf("A", "B", "C", "D", "E", "F"),
    var unitList: List<String> = listOf("0", "1", "2", "3", "4", "6"),

    ) //: parc
    : Parcelable