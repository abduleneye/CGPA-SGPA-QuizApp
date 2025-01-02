package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.domain.model

import GpCalculatorPrototype.Data.FourGpData

data class FourSgpaResult(
    val resultEntries: ArrayList<FourGpData> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
)
