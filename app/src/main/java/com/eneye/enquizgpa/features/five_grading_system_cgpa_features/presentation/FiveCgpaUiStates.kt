package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation

import android.os.Parcelable
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.ResultTracker
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.FiveErrorPassedValues
import kotlinx.parcelize.RawValue


@kotlinx.parcelize.Parcelize
data class FiveCgpaUiStates(

    var sgpaResultNames: ArrayList<String> = ArrayList(),
    var displayedResultForFiveCgpaCalculation: @RawValue ArrayList<SgpaResultDisplayFormatForFiveCgpaCalculation> = ArrayList<SgpaResultDisplayFormatForFiveCgpaCalculation>(),
    var sgpaListToBeCalculated: @RawValue ArrayList<ResultTracker> = arrayListOf(),
    var cgpaList: ArrayList<Float> = arrayListOf(),
    var cgpa: String = "",
    var helperText: String = "",
    var operatorIconState: Boolean = false,
    var remark: String = "",
    var fiveCgpaFinalResult: String = "",
    var gpaDescriptor: String = "",
    var saveResultDBVisibilty: Boolean = false,
    var saveResultAs: String = "",
    var defaultLabelSRA: String = FiveErrorPassedValues.labelForSRA,
    var defaultLabelColourSRA: Long = 0xFFB6B07B,
    var newHelperText: String = "hello",
    var fiveCgpaIntroDialogBoxVisibility: Boolean = false,


    ) : Parcelable