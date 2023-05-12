package com.example.gpcalculator.myViewModels

import androidx.lifecycle.ViewModel
import com.example.gpcalculator.ScreenElements.DialogBoxState
import com.example.gpcalculator.ScreenElements.DialogBoxUiEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FirstViewModel:ViewModel() {

    private  var _dbState = MutableStateFlow(DialogBoxState())
          var dbState = _dbState.asStateFlow()


    fun onEvent(event: DialogBoxUiEvents){

        when(event){

           is DialogBoxUiEvents.showDBox  ->  {

               _dbState.update {
                   it.copy(
                       dialogBoxVisibility = true
                   )
               }

            }

            is DialogBoxUiEvents.hideDBox -> {
                _dbState.update {
                    it.copy(
                        dialogBoxVisibility = false
                    )
                }
            }

            is DialogBoxUiEvents.showUnitMenuDropDown -> {

                    _dbState.update {
                        it.copy(
                            isUnitDropDownMenuExpanded = true
                        )
                }

            }

            is DialogBoxUiEvents.hideUnitMenuDropDown -> {

                _dbState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = false
                    )
                }
            }

            is  DialogBoxUiEvents.showGradeMenuDropDown -> {
                _dbState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = true
                    )
                }
            }

            is  DialogBoxUiEvents.hideGradeMenuDropDown -> {
                _dbState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = false
                    )
                }
            }

            is DialogBoxUiEvents.setSelectedCourseGrade -> {

                _dbState.update {
                    it.copy(
                        selectedCourseGrade = event.grade
                    )
                }

            }

            is DialogBoxUiEvents.setSelectedCourseUnit -> {
                _dbState.update {
                    it.copy(
                        selectedCourseUnit = event.unit
                    )
                }
            }
            is DialogBoxUiEvents.setCourseCode -> {
                _dbState.update {
                    it.copy(
                        courseCode = event.courseCode
                    )
                }
            }

            else -> {}
        }

    }
}