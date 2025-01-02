package GpCalculatorPrototype.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FiveCoursesUnitPointArrayList(

    val sixUnitA: ArrayList<Int> = ArrayList<Int>(),
    val sixUnitB: ArrayList<Int> = ArrayList<Int>(),
    val sixUnitC: ArrayList<Int> = ArrayList<Int>(),
    val sixUnitD: ArrayList<Int> = ArrayList<Int>(),
    val sixUnitE: ArrayList<Int> = ArrayList<Int>(),
    val sixUnitF: ArrayList<Int> = ArrayList<Int>(),

    val fourUnitA: ArrayList<Int> = ArrayList<Int>(),
    val fourUnitB: ArrayList<Int> = ArrayList<Int>(),
    val fourUnitC: ArrayList<Int> = ArrayList<Int>(),
    val fourUnitD: ArrayList<Int> = ArrayList<Int>(),
    val fourUnitE: ArrayList<Int> = ArrayList<Int>(),
    val fourUnitF: ArrayList<Int> = ArrayList<Int>(),

    val threeUnitA: ArrayList<Int> = ArrayList<Int>(),
    val threeUnitB: ArrayList<Int> = ArrayList<Int>(),
    val threeUnitC: ArrayList<Int> = ArrayList<Int>(),
    val threeUnitD: ArrayList<Int> = ArrayList<Int>(),
    val threeUnitE: ArrayList<Int> = ArrayList<Int>(),
    val threeUnitF: ArrayList<Int> = ArrayList<Int>(),


    val twoUnitA: ArrayList<Int> = ArrayList<Int>(),
    val twoUnitB: ArrayList<Int> = ArrayList<Int>(),
    val twoUnitC: ArrayList<Int> = ArrayList<Int>(),
    val twoUnitD: ArrayList<Int> = ArrayList<Int>(),
    val twoUnitE: ArrayList<Int> = ArrayList<Int>(),
    val twoUnitF: ArrayList<Int> = ArrayList<Int>(),

    var oneUnitA: ArrayList<Int> = ArrayList<Int>(),
    val oneUnitB: ArrayList<Int> = ArrayList<Int>(),
    val oneUnitC: ArrayList<Int> = ArrayList<Int>(),
    val oneUnitD: ArrayList<Int> = ArrayList<Int>(),
    val oneUnitE: ArrayList<Int> = ArrayList<Int>(),
    val oneUnitF: ArrayList<Int> = ArrayList<Int>()
) : Parcelable