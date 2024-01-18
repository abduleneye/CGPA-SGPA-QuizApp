package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

sealed interface FiveCgpaUiEvents {

    object showFiveCgpaSaveResultDB : FiveCgpaUiEvents
    object hideFiveCgpaSaveResultDB : FiveCgpaUiEvents
    object resetBackToDefaultValueFromErrorSRAFiveCgpa : FiveCgpaUiEvents
    object saveFiveCgpaResult : FiveCgpaUiEvents

    object helpFiveCgpa : FiveCgpaUiEvents
    object loadFiveCgpaResult : FiveCgpaUiEvents


    data class setFiveCgpaSRA(var saveResultAs: String) : FiveCgpaUiEvents


}