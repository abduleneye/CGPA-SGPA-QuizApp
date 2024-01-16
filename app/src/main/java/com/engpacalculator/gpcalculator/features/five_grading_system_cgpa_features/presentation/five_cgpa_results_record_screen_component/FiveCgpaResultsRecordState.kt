package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultIntroEntity

data class FiveCgpaResultsRecordState(
    var resultItems: List<UniFiveSgpaResultEntity> = emptyList(),
    var introResultsItems: List<UniFiveSgpaResultIntroEntity> = emptyList()

)