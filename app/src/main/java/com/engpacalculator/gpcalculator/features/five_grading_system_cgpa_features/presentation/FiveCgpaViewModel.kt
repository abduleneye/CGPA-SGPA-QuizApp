package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FiveCgpaViewModel constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _fiveCgpaUiStates = MutableStateFlow(FiveCgpaUiStates())

    var fiveCgpaUiStates = _fiveCgpaUiStates.asStateFlow()

    fun onEvent(onEvents: FiveCgpaUiEvents) {
        when (onEvents) {
            is FiveCgpaUiEvents.showSaveResultDB -> {
                _fiveCgpaUiStates.update {
                    it.copy(
                        saveResultDBVisibilty = true
                    )
                }


            }

            is FiveCgpaUiEvents.hideSaveResultDB -> {
                _fiveCgpaUiStates.update {
                    it.copy(
                        saveResultDBVisibilty = false
                    )
                }
            }

            is FiveCgpaUiEvents.help -> {

                _fiveCgpaUiStates.update {
                    it.copy(
                        newHelperText = "Ahh..."
                    )
                }
            }

            else -> {

            }
        }
    }
}