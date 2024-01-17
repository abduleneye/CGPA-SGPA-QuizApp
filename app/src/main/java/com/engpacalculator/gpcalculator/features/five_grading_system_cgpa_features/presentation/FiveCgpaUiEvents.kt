package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

sealed interface FiveCgpaUiEvents {

    object showSaveResultDB : FiveCgpaUiEvents
    object hideSaveResultDB : FiveCgpaUiEvents
    object resetBackToDefaultValueFromErrorSRA : FiveCgpaUiEvents
    object save : FiveCgpaUiEvents

    object help : FiveCgpaUiEvents


    data class setSRA(var saveResultAs: String) : FiveCgpaUiEvents


}