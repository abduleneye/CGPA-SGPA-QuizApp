package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.results_record_screen_component

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.UniFiveSgpaResultIntroEntity

data class ResultsRecordState(
    var resultItems: List<UniFiveSgpaResultEntity> = emptyList(),
    var introResultsItems: List<UniFiveSgpaResultIntroEntity> = emptyList()

)