package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.FiveErrorPassedValues
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.ResultTracker
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFourCgpaCalculation
import kotlinx.parcelize.RawValue


@kotlinx.parcelize.Parcelize
data class FourCgpaUiStates(

    var sgpaResultNames: ArrayList<String> = ArrayList(),
    var displayedResultForFourCgpaCalculation: @RawValue ArrayList<SgpaResultDisplayFormatForFourCgpaCalculation> = ArrayList<SgpaResultDisplayFormatForFourCgpaCalculation>(),
    var sgpaListToBeCalculated: @RawValue ArrayList<ResultTracker> = arrayListOf(),
    var cgpaList: ArrayList<Float> = arrayListOf(),
    var cgpa: String = "",
    var helperText: String = "",
    var operatorIconState: Boolean = false,
    val remark: String = "",
    var fourCgpaFinalResult: String = "",
    var gpaDescriptor: String = "",
    var saveResultDBVisibilty: Boolean = false,
    var saveResultAs: String = "",
    var defaultLabelSRA: String = FiveErrorPassedValues.labelForSRA,
    var defaultLabelColourSRA: Long = 0xFFB6B07B,
    var newHelperText: String = "hello",
    var fourCgpaIntroDialogBoxVisibility: Boolean = false


) : Parcelable