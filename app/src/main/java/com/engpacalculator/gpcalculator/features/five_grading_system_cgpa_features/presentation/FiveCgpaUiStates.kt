package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.ResultTracker
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.ErrorPassedValues
import kotlinx.parcelize.RawValue


@kotlinx.parcelize.Parcelize
data class FiveCgpaUiStates(

    var displayedResultForFiveCgpaCalculation: @RawValue ArrayList<SgpaResultDisplayFormatForFiveCgpaCalculation> = ArrayList<SgpaResultDisplayFormatForFiveCgpaCalculation>(),
    var sgpaListToBeCalculated: @RawValue ArrayList<ResultTracker> = arrayListOf(),
    var cgpaList: ArrayList<Float> = arrayListOf(),
    var cgpa: String = "",
    var helperText: String = "",
    var operatorIconState: Boolean = false,
    val remark: String = "",
    var fiveCgpaFinalResult: String = "",
    var gpaDescriptor: String = "",
    var saveResultDBVisibilty: Boolean = false,
    val saveResultAs: String = "",
    val defaultLabelSRA: String = ErrorPassedValues.labelForSRA,
    val defaultLabelColourSRA: Long = 0xFFB6B07B,
    val newHelperText: String = "hello"


) : Parcelable {
}