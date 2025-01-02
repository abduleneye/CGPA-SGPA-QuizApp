package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SgpaResultDisplayFormatForFiveCgpaCalculation(
    var resultSelected: Boolean,
    var resultName: String,
    var resultSgpa: String,
) : Parcelable
