package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity

data class FiveCgpaResultsRecordState(
    var resultItems: List<FiveCgpaResultEntity> = emptyList(),
//    var introResultsItems: List<UniFiveSgpaResultIntroEntity> = emptyList()

)