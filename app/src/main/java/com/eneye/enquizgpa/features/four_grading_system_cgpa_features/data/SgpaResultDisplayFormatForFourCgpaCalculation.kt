package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SgpaResultDisplayFormatForFourCgpaCalculation(
    var resultSelected: Boolean,
    var resultName: String,
    var resultSgpa: String,
) : Parcelable
