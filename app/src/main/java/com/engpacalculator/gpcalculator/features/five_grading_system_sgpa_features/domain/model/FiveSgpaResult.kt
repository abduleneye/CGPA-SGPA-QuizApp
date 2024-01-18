package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.model

import GpCalculatorPrototype.Data.GpData

data class UniFiveSgpaResult(
    val resultEntries: ArrayList<GpData> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
)
