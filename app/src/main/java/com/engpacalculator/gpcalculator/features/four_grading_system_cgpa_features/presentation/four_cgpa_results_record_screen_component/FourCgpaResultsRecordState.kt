package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity


@kotlinx.parcelize.Parcelize

data class FourCgpaResultsRecordState(
    var resultItems: List<FourCgpaResultEntity> = emptyList(),
//    var introResultsItems: List<UniFourSgpaResultIntroEntity> = emptyList()

) : Parcelable