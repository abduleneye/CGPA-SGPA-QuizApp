package GpCalculatorPrototype.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class FourCourseDataEntries(

    val coursesDataEntry: @RawValue ArrayList<FourGpData> = ArrayList<FourGpData>(),
    var sixUnitCoursesPointSum: Double = 0.0,
    var fiveUnitCoursesPointSum: Double = 0.0,
    var fourUnitCoursesPointSum: Double = 0.0,
    var threeUnitCoursesPointSum: Double = 0.0,
    var twoUnitCoursesPointSum: Double = 0.0,
    var oneUnitCoursesPointSum: Double = 0.0,
    var totalCoursesPointSum: Double = 0.0,

    var gradeList: List<String> = listOf("A", "AB", "B", "BC", "C", "CD", "D", "E", "F"),
    var unitList: List<String> = listOf("1", "2", "3", "4", "5", "6"),

    ) //: parc
    : Parcelable