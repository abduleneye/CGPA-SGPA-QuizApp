package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity

@kotlinx.parcelize.Parcelize

data class FiveSgpaResultsRecordState(
    var resultItems: List<FiveSgpaResultEntity> = emptyList(),
//    var introResultsItems: List<UniFiveSgpaResultIntroEntity> = emptyList()

) : Parcelable