package GpCalculatorPrototype.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FourCoursesUnitPointArrayList(

    val sixUnitA: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitAB: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitB: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitBC: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitC: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitCD: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitD: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitE: ArrayList<Double> = ArrayList<Double>(),
    val sixUnitF: ArrayList<Double> = ArrayList<Double>(),


    val fiveUnitA: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitAB: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitB: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitBC: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitC: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitCD: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitD: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitE: ArrayList<Double> = ArrayList<Double>(),
    val fiveUnitF: ArrayList<Double> = ArrayList<Double>(),

    val fourUnitA: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitAB: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitB: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitBC: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitC: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitCD: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitD: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitE: ArrayList<Double> = ArrayList<Double>(),
    val fourUnitF: ArrayList<Double> = ArrayList<Double>(),

    val threeUnitA: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitAB: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitB: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitBC: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitC: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitCD: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitD: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitE: ArrayList<Double> = ArrayList<Double>(),
    val threeUnitF: ArrayList<Double> = ArrayList<Double>(),


    val twoUnitA: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitAB: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitB: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitBC: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitC: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitCD: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitD: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitE: ArrayList<Double> = ArrayList<Double>(),
    val twoUnitF: ArrayList<Double> = ArrayList<Double>(),

    val oneUnitA: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitAB: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitB: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitBC: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitC: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitCD: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitD: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitE: ArrayList<Double> = ArrayList<Double>(),
    val oneUnitF: ArrayList<Double> = ArrayList<Double>(),
) : Parcelable