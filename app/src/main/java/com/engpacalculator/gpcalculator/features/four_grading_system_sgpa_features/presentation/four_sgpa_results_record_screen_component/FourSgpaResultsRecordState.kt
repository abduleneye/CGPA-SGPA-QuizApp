package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity

data class FourSgpaResultsRecordState(
    var resultItems: List<FourSgpaResultEntity> = emptyList(),
//    var introResultsItems: List<UniFiveSgpaResultIntroEntity> = emptyList()

)