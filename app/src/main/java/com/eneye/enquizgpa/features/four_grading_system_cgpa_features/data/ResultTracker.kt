package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResultTracker(
    var resultName: String,
    var sgpaResult: String,
    var id: Int,
) : Parcelable
