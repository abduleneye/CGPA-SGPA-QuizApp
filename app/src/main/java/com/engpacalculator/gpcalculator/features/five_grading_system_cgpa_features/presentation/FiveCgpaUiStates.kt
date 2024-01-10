package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import kotlinx.parcelize.RawValue


@kotlinx.parcelize.Parcelize
data class FiveCgpaUiStates(

    var displayedResultForFiveCgpaCalculation: @RawValue ArrayList<SgpaResultDisplayFormatForFiveCgpaCalculation> = ArrayList<SgpaResultDisplayFormatForFiveCgpaCalculation>()
) : Parcelable