package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.domain.model

import GpCalculatorPrototype.Data.FiveGpData

data class FiveSgpaResult(
    val resultEntries: ArrayList<FiveGpData> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
)
