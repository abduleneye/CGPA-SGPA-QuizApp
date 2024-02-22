package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation

import GpCalculatorPrototype.Data.FiveCourseDataEntries
import GpCalculatorPrototype.Data.FiveCourseMaps
import GpCalculatorPrototype.Data.FiveCoursesUnitPointArrayList
import GpCalculatorPrototype.Data.FiveGpData
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.ResultTracker
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.domain.repository.FiveCgpaResultRepository
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component.FiveCgpaResultsRecordState
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.FiveErrorMessages
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.FiveErrorPassedValues
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.FiveSgpaResultRepository
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaResultsRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random
import kotlin.system.measureTimeMillis

@HiltViewModel
class FiveGpaViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val myFiveSgpaRepository: FiveSgpaResultRepository,
    private val myFiveCgpaRepository: FiveCgpaResultRepository,


    ) : ViewModel() {
    companion object {
        private const val FIVE_SGPA_COURSE_ENTRIES_KEY =
            "my_five_sgpa_course_entry_state"
        private const val FIVE_SGPA_UI_STATE_KEY = "my_five_sgpa_ui_state"
        private const val FIVE_SGPA_RESULT_INTRO_DBASE = "my_five_sgpa_result_intro_data_base"
        private const val FIVE_CGPA_UI_STATE_KEY = "my_five_cgpa_ui_state"
        private const val FIVE_CGPA_RESULT_INTRO_DBASE = "my_five_Cgpa_result_intro_data_base"

    }

    private val coursePointObj = FiveCoursesUnitPointArrayList()
    private val courseMapObj = FiveCourseMaps()
    private val coursesDataEntryObj = FiveCourseDataEntries()
    private var result = ""
    var coursesUnitSubList = ArrayList<Int>()


    private var _courseEntries = MutableStateFlow(
        savedStateHandle.get(FIVE_SGPA_COURSE_ENTRIES_KEY)
            ?: FiveCourseDataEntries().coursesDataEntry
    )
    var courseEntries = _courseEntries.asStateFlow()

    private var _fiveSgpaUiState =
        MutableStateFlow(savedStateHandle.get(FIVE_SGPA_UI_STATE_KEY) ?: FiveSgpaUiStates())
    var fiveSgpaUiState = _fiveSgpaUiState.asStateFlow()

    private var _fiveCgpaUiState =
        MutableStateFlow(savedStateHandle.get(FIVE_CGPA_UI_STATE_KEY) ?: FiveCgpaUiStates())
    var fiveCgpaUiState = _fiveCgpaUiState.asStateFlow()


    private var _fiveSgparesultIntroDB = MutableStateFlow(
        savedStateHandle.get(
            FIVE_SGPA_RESULT_INTRO_DBASE
        ) ?: FiveSgpaResultsRecordState()
    )
    val fiveSgparesultIntroDB = _fiveSgparesultIntroDB.asStateFlow()

    private var _fiveCgpaResultIntroDB = MutableStateFlow(
        savedStateHandle.get(
            FIVE_CGPA_RESULT_INTRO_DBASE
        ) ?: FiveCgpaResultsRecordState()
    )
    val fiveCgpaResultIntroDB = _fiveCgpaResultIntroDB.asStateFlow()


    init {
        loadFiveSgpaData()
        loadFiveCgpaData()
    }

    private fun loadFiveCgpaData(chkBoxState: Boolean = false, pseudoIndex: Int = 0) {
        viewModelScope.launch {

            myFiveCgpaRepository.GetFiveCgpaResultRecordDao()
                .collect { result ->

                    _fiveCgpaResultIntroDB.update {
                        it.copy(
                            resultItems = result
                        )
                    }
                    savedStateHandle.set(FIVE_CGPA_RESULT_INTRO_DBASE, _fiveCgpaResultIntroDB.value)

                    //  _resultIntroDB.value.resultItems = result

                }
        }
    }


    private fun loadFiveSgpaData(chkBoxState: Boolean = false, pseudoIndex: Int = 0) {
        viewModelScope.launch {
            _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.clear()
            savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            myFiveSgpaRepository.GetFiveSgpaResultRecordDao()
                .collect { result ->

                    _fiveSgparesultIntroDB.update {
                        it.copy(
                            resultItems = result
                        )
                    }
                    savedStateHandle.set(FIVE_SGPA_RESULT_INTRO_DBASE, _fiveSgparesultIntroDB.value)


                    for (i in 0 until result.size) {

                        _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.add(
                            SgpaResultDisplayFormatForFiveCgpaCalculation(
                                resultSelected = false,
                                resultName = result.get(i).resultName,
                                resultSgpa = result.get(i).gp
                            )
                        )
                        savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                    }
                    //  _resultIntroDB.value.resultItems = result

                }


        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: FiveGpaUiEvents) {

        when (event) {
            // var TAG: String = "list"


            is FiveGpaUiEvents.showFiveCgpaSaveResultDB -> {
                _fiveCgpaUiState.update {
                    it.copy(
                        saveResultDBVisibilty = true
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            }

            is FiveGpaUiEvents.hideFiveCgpaSaveResultDB -> {
                _fiveCgpaUiState.update {
                    it.copy(
                        saveResultDBVisibilty = false,
                        saveResultAs = "",
                        defaultLabelColourSRA = FiveErrorPassedValues.errorPassedColour,
                        defaultLabelSRA = FiveErrorPassedValues.labelForSRA,

                        )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

            }

            is FiveGpaUiEvents.helpFiveCgpa -> {

                _fiveCgpaUiState.update {
                    it.copy(
                        newHelperText = "Ahh..."
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

            }

            is FiveGpaUiEvents.setFiveCgpaSRA -> {
                _fiveCgpaUiState.update {
                    it.copy(
                        saveResultAs = event.saveResultAs
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            }

            is FiveGpaUiEvents.saveFiveCgpaResult -> {


                textFieldsErrorCheckSaveFiveCgpaResultAsDataEntry()


            }


            is FiveGpaUiEvents.executeCgpaCalculation -> {
                if (_fiveCgpaUiState.value.sgpaListToBeCalculated.isNotEmpty()) {
                    for (i in 0.._fiveCgpaUiState.value.sgpaListToBeCalculated.size - 1) {
                        _fiveCgpaUiState.value.cgpaList.add(
                            _fiveCgpaUiState.value.sgpaListToBeCalculated.get(
                                i
                            ).sgpaResult.toDouble().toFloat()
                        )
                        savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

                    }

                    _fiveCgpaUiState.update {
                        it.copy(
                            cgpa = String.format(
                                "%.2f",
                                (_fiveCgpaUiState.value.cgpaList.sum()
                                    .toDouble() / _fiveCgpaUiState.value.cgpaList.size.toDouble())

                            )
                        )
                    }
                    GpaDescriptor(_fiveCgpaUiState.value.cgpa.toFloat(), "cgpa")
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                }

                Log.d(
                    "CGPA",
                    "Your sum is ${_fiveCgpaUiState.value.cgpaList.sum()} and size is ${_fiveCgpaUiState.value.cgpaList.size}"
                )
                Log.d("CGPA", "Your cgpa is ${_fiveCgpaUiState.value.cgpa}")

                //_fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.clear()
                _fiveCgpaUiState.value.cgpaList.clear()
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

                //_fiveCgpaUiState.value.sgpaListToBeCalculated.clear()
                // loadFiveSgpaData()
            }

            is FiveGpaUiEvents.onCheckChanged -> {


                _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.get(event.index).resultSelected =
                    event.isChecked
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                for (i in 0.._fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.size - 1) {
                    if (_fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation[i].resultSelected) {
                        _fiveCgpaUiState.update {
                            it.copy(operatorIconState = true)

                        }
                        savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

                        Log.d(
                            "StatusIconCheck",
                            "Your status are ${_fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation}"
                        )

                    } else if (_fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation[i].resultSelected == false && event.isChecked == false) {
                        _fiveCgpaUiState.update {
                            it.copy(operatorIconState = false)
                        }
                        savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

                    }

                    "Your status are ${_fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation}"


                }

                val randomNumber = Random.nextInt(1, 1000)
                _fiveCgpaUiState.update {
                    it.copy(
                        helperText = randomNumber.toString()
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)



                if (event.isChecked == true) {
                    _fiveCgpaUiState.update {
                        it.copy(operatorIconState = true)
                    }
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                    _fiveCgpaUiState.value.sgpaListToBeCalculated.add(
                        //index = event.index,
                        ResultTracker(
                            id = event.index,
                            sgpaResult = event.sgpaNeeded,
                            resultName = event.resultNameRef
                        )
                    )
                    _fiveCgpaUiState.value.sgpaResultNames.add(event.resultNameRef)
                } else {
                    _fiveCgpaUiState.value.sgpaListToBeCalculated.removeIf {
                        it.id == event.index
                    }
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.sgpaResultNames.remove(event.resultNameRef)
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                }

                // println(_fiveCgpaUiState.value.sgpaListToBeCalculated)
                Log.d("List of sgpa", "${_fiveCgpaUiState.value.sgpaListToBeCalculated}")
            }

            is FiveGpaUiEvents.DeleteFiveGpaResultByReference -> {
                _fiveCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                viewModelScope.launch {
                    myFiveSgpaRepository.FiveSgpaResultToBeDeleted(event.fiveSgpaResultName)
                    _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.cgpaList.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.sgpaListToBeCalculated.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                }
            }

            is FiveGpaUiEvents.DeleteFiveCgpaResultByReference -> {
                _fiveCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

                viewModelScope.launch {
                    myFiveCgpaRepository.FiveCgpaResultToBeDeleted(event.fiveCgpaResultName)
//                    _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.clear()
//                    _fiveCgpaUiState.value.cgpaList.clear()
//                    _fiveCgpaUiState.value.sgpaListToBeCalculated.clear()

                }
            }


            is FiveGpaUiEvents.DeleteResult -> {
                _fiveCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                viewModelScope.launch {
                    myFiveSgpaRepository.DeleteFiveSgpaResult(event.fiveSgpaResultName)
                    _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.cgpaList.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.sgpaListToBeCalculated.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


                }

            }

            is FiveGpaUiEvents.showResultDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        resultDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideFiveSgpaSaveResultDB -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        saveResultAsDialogBoxVisibility = false,
                        saveResultAs = ""

                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.setSRA -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        saveResultAs = event.savedResultName

                    )
                }

            }

            is FiveGpaUiEvents.saveFiveSgpaResult -> {

//                _fiveSgpaUiState.update {
//                    it.copy(
//                        fiveSgpaSRAToastNotifier = true
//                    )
//                }
                textFieldsErrorCheckAndDuplicateEntrySaveFiveSgpaResultAsDataEntry(_fiveSgpaUiState.value.saveResultAs)


            }

            is FiveGpaUiEvents.resetBackToDefaultValueFromErrorSRA -> {
                resetFiveSgpaSRADBox()

            }

            is FiveGpaUiEvents.resetBackToDefaultValueFromErrorSRAFiveCgpa -> {
                resetFiveCgpaSRADBox()

            }

            is FiveGpaUiEvents.replaceEditedInEntriesToArrayList -> {
                textFieldsErrorCheckEditedCourseDataEntry()
            }

            is FiveGpaUiEvents.editItemsEntries -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        courseCode = event.courseCodeEdit,
                        selectedCourseUnit = event.unitEdit,
                        selectedCourseGrade = event.gradeEdit

                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.updateCourseIndexEntry -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        courseEntryIndex = event.entryIndex
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showCourseEntryEditDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = true,
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideCourseEntryEditDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }


            is FiveGpaUiEvents.resetResultField -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        fiveSgpaFinalResult = "new val"
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showDataEntryDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        courseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

            is FiveGpaUiEvents.hideDataEntryDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        allReadyInList = false,
                        courseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showUnitMenuDropDown -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

            is FiveGpaUiEvents.hideUnitMenuDropDown -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

            is FiveGpaUiEvents.showGradeMenuDropDown -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideGradeMenuDropDown -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.setSelectedCourseGrade -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        selectedCourseGrade = event.grade
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

            is FiveGpaUiEvents.setSelectedCourseUnit -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        selectedCourseUnit = event.unit
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.deleteCourseEntry -> {

                _courseEntries.value.removeAt(event.itemToRemove)
                savedStateHandle.set(
                    FIVE_SGPA_COURSE_ENTRIES_KEY,
                    _courseEntries.value
                )
                _fiveSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.removeAt(event.itemToRemove)
                _fiveSgpaUiState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString()
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.setCourseCode -> {

                if (_fiveSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.contains(event.courseCode.uppercase())) {
                    _fiveSgpaUiState.update {
                        it.copy(
                            allReadyInList = true,
                            matchAlreadyInCourseEntry = event.courseCode.uppercase()
                        )
                    }
                } else {
                    _fiveSgpaUiState.update {
                        it.copy(
                            allReadyInList = false,
                            matchAlreadyInCourseEntry = ""

                        )
                    }

                }
                _fiveSgpaUiState.update {
                    it.copy(
                        courseCode = event.courseCode.replace(" ", "")
                    )


                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

//                } else {
//                    _fiveSgpaUiState.update {
//                        it.copy(
//                            courseCode = event.courseCode
//                        )
//
//
//                    }
//                }


            }

            is FiveGpaUiEvents.addEntriesToArrayList -> {

                textFieldsErrorCheckCourseDataEntry()


            }

            is FiveGpaUiEvents.setTotalCreditLoad -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        totalCreditLoad = event.totalCreditLoad
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.setTotalCourses -> {
//                var use = event.totalCourses
//                if (use[0] == '0') {
//                    use = use.replace("0", "")
//                    _fiveSgpaUiState.update {
//                        it.copy(
//                            totalCourses = use
//                        )
//                    }
//                    savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)
//
//
//                } else if (use[0] == '0' && use[1] == '0') {
//                    use = use.replace("0", "")
//                    _fiveSgpaUiState.update {
//                        it.copy(
//                            totalCourses = use
//                        )
//                    }
//                    savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)
//
//
//                } else {
                _fiveSgpaUiState.update {
                    it.copy(
                        totalCourses = event.totalCourses
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


                // }


            }

            is FiveGpaUiEvents.hideBaseEntryDBox -> {
                textFieldsErrorCheckBaseEntryDB()

            }

            is FiveGpaUiEvents.showSaveResultDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        saveResultAsDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

//            is FiveGpaUiEvents.hideFiveSgpaSaveResultDB -> {
//                _fiveSgpaUiState.update {
//
//                    it.copy(
//                        saveResultAsDialogBoxVisibility = false,
//                    )
//                }
//                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)
//
//            }

            is FiveGpaUiEvents.resetAlreadyInList -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        allReadyInList = false
                    )

                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

            is FiveGpaUiEvents.showCourseDataEntriesContextmenu -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        courseItemsDropDownVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

            is FiveGpaUiEvents.hideCourseDataEntriesContextmenu -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        courseItemsDropDownVisibility = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.resetTotalEntries -> {


                if (_fiveSgpaUiState.value.totalCourses > 0.toString()) {


                    _fiveSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.clear()
                    _courseEntries.value.clear()
                    _fiveSgpaUiState.update {
                        it.copy(
                            //totalCourses = "",
                            totalCreditLoad = "",
                            enteredCourses = "0",

                            //baseEntryDialogBoxVisibility = true
                        )
                    }
                    savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


                } else {


                }


            }

            is FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        defaultLabelColourTNOC = FiveErrorPassedValues.errorPassedColour,
                        defaultLabelTNOC = FiveErrorPassedValues.labelForTNOC

                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCC -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        defaultLabelColourCC = FiveErrorPassedValues.errorPassedColour,
                        defaultEnteredCourseCodeLabel = FiveErrorPassedValues.enterCourseCodeLabel

                    )
                }

            }

            is FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsECC -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        defaultLabelColourECC = FiveErrorPassedValues.errorPassedColour,
                        defaultEditCourseCodeLabel = FiveErrorPassedValues.editCourseCodeLabel

                    )
                }

            }


            is FiveGpaUiEvents.resetDefaultValuesFromErrorsTNOCL -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        defaultLabelTNOCL = FiveErrorPassedValues.labelForTNOCC,
                        //defaultLabelColourTNOCL = FiveErrorPassedValues.errorPassedColour

                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showEditBaseEntryDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        editBaseEntryDialogBoxVisibility = true,
                        errorToastMessageVisibilityETNOCDB = true

                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideEditBaseEntryDBox -> {

                textFieldsErrorEditedCheckBaseEntryDB()

                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideEditBaseEntryRegardlessDBox -> {

                if (_fiveSgpaUiState.value.enteredCourses == "0") {

                    _fiveSgpaUiState.update {
                        it.copy(
                            editBaseEntryDialogBoxVisibility = false,
                            totalCourses = _fiveSgpaUiState.value.prevTotalNumberOfCourses
                        )
                    }

                    savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

                } else if (_fiveSgpaUiState.value.enteredCourses != "0") {

                    _fiveSgpaUiState.update {
                        it.copy(
                            editBaseEntryDialogBoxVisibility = false,
                            totalCourses = _fiveSgpaUiState.value.enteredCourses

                        )
                    }

                    savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


                }


            }


            is FiveGpaUiEvents.showBaseEntryDBox -> {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    greetings()
                }

                _fiveSgpaUiState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }


            is FiveGpaUiEvents.executeCalculation -> {

                _fiveSgpaUiState.update {
                    it.copy(
                        changeDoneIcon = true
                    )
                }

                var execTotalUnit: Int = 0


                for (i in 1.._fiveSgpaUiState.value.totalCourses.toInt()) {
                    coursesUnitSubList.add(_courseEntries.value[i - 1].courseUnit)
                    savedStateHandle.set(
                        FIVE_SGPA_COURSE_ENTRIES_KEY,
                        _courseEntries.value
                    )

                }
                execTotalUnit = coursesUnitSubList.sum()
                _fiveSgpaUiState.update {
                    it.copy(
                        totalCreditLoad = execTotalUnit.toString()
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)



                courseValueMapper(_courseEntries.value)
                result = operations(_fiveSgpaUiState.value.totalCreditLoad.toInt())
                _fiveSgpaUiState.update {
                    it.copy(
                        fiveSgpaFinalResult = result
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


                GpaDescriptor(_fiveSgpaUiState.value.fiveSgpaFinalResult.toFloat(), "sgpa")

                onReExecuteCalculationClearArrayField()


            }

            is FiveGpaUiEvents.hideBaseEntryRegardlessDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = false,
                        totalCourses = "",
                        prevTotalNumberOfCourses = ""

                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.setPrevTotalCourses -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        prevTotalNumberOfCourses = event.text
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showClearConfirmationDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideClearConfirmationDBox -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showHomeAdShimmerEffect -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        homeAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideHomeAdShimmerEffect -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        homeAdShimmerEffectVisibility = false
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.showAboutAdShimmerEffect -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        aboutAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.hideAboutAdShimmerEffect -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        aboutAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            }

            is FiveGpaUiEvents.setTotalNumberOfEditedCourses -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        editedNumberOfCourses = event.noOfEditedTotalCourse
                    )
                }
            }

            is FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCU -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        pickedCourseUnitDefaultLabel = FiveErrorPassedValues.enterCourseUnitLabel,
                        defaultLabelColourCU = FiveErrorPassedValues.dropDownErrorPassedColour
                    )
                }
            }

            is FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCG -> {
                _fiveSgpaUiState.update {
                    it.copy(
                        pickedCourseGradeDefaultLabel = FiveErrorPassedValues.enterCourseGradeLabel,
                        defaultLabelColourCG = FiveErrorPassedValues.dropDownErrorPassedColour
                    )
                }
            }

            is FiveGpaUiEvents.showFiveCgpaIntroDialogBox -> {
                _fiveCgpaUiState.update {
                    it.copy(
                        fiveCgpaIntroDialogBoxVisibility = true

                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

            }

            is FiveGpaUiEvents.hideFiveCgpaIntroDialogBox -> {
                _fiveCgpaUiState.update {
                    it.copy(
                        fiveCgpaIntroDialogBoxVisibility = false

                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

            }


            else -> {}
        }

    }


    private fun textFieldsErrorCheckBaseEntryDB() {

        if (_fiveSgpaUiState.value.totalCourses.isEmpty() || _fiveSgpaUiState.value.totalCourses == "0" || _fiveSgpaUiState.value.totalCourses == "00" || _fiveSgpaUiState.value.totalCourses.get(
                0
            ) == '0'
        ) {

            _fiveSgpaUiState.update {
                it.copy(
                    defaultLabelTNOC = FiveErrorMessages.errorLabelMessageForTNOC,
                    defaultLabelColourTNOC = FiveErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else if (_fiveSgpaUiState.value.totalCreditLoad.isNotEmpty()) {
            _fiveSgpaUiState.update {
                it.copy(
                    baseEntryDialogBoxVisibility = false,
                    courseEntryDialogBoxVisibility = true

                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        }


    }

    private fun textFieldsErrorEditedCheckBaseEntryDB() {

        if (_fiveSgpaUiState.value.totalCourses == _fiveSgpaUiState.value.prevTotalNumberOfCourses && _fiveSgpaUiState.value.enteredCourses == "0") {
            _fiveSgpaUiState.update {
                it.copy(
                    errorMessageHolderForETNOCDBToastMessage = "No changes made",
                    editBaseEntryDialogBoxVisibility = false,
                    errorToastMessageVisibilityETNOCDB = false,

                    )
            }
        } else if (_fiveSgpaUiState.value.editedNumberOfCourses.isEmpty() || _fiveSgpaUiState.value.editedNumberOfCourses == "0" || _fiveSgpaUiState.value.editedNumberOfCourses == "00" || _fiveSgpaUiState.value.editedNumberOfCourses.get(
                0
            ) == '0'
        ) {
            if (_fiveSgpaUiState.value.enteredCourses == "0") {
                _fiveSgpaUiState.update {
                    it.copy(
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be empty",
                        totalCourses = _fiveSgpaUiState.value.prevTotalNumberOfCourses,
                        editedNumberOfCourses = _fiveSgpaUiState.value.prevTotalNumberOfCourses,
                        errorToastMessageVisibilityETNOCDB = true,


                        )
                }
            } else if (_fiveSgpaUiState.value.enteredCourses != "0") {
                _fiveSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be empty",
                        totalCourses = _fiveSgpaUiState.value.enteredCourses,
                        editedNumberOfCourses = _fiveSgpaUiState.value.enteredCourses

                    )
                }
            }

            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else if (_fiveSgpaUiState.value.editedNumberOfCourses == "0") {

            if (_fiveSgpaUiState.value.enteredCourses == "0") {
                _fiveSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be 0",
                        totalCourses = _fiveSgpaUiState.value.prevTotalNumberOfCourses,
                        editedNumberOfCourses = _fiveSgpaUiState.value.prevTotalNumberOfCourses

                    )
                }
            } else if (_fiveSgpaUiState.value.enteredCourses != "0") {
                _fiveSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be 0",
                        totalCourses = _fiveSgpaUiState.value.enteredCourses,
                        editedNumberOfCourses = _fiveSgpaUiState.value.enteredCourses

                    )
                }
            }

            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else if (_fiveSgpaUiState.value.editedNumberOfCourses.isNotEmpty() && _fiveSgpaUiState.value.enteredCourses != "0") {

            if (_fiveSgpaUiState.value.editedNumberOfCourses.toInt() < fiveSgpaUiState.value.enteredCourses.toInt()) {
                _fiveSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be less than already entered courses",
                        totalCourses = _fiveSgpaUiState.value.enteredCourses,
                        editedNumberOfCourses = _fiveSgpaUiState.value.enteredCourses

                    )
                }
            } else if (_fiveSgpaUiState.value.editedNumberOfCourses >= _fiveSgpaUiState.value.enteredCourses) {
                _fiveSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = false,
                        errorMessageHolderForETNOCDBToastMessage = "successfully updated",
                        totalCourses = _fiveSgpaUiState.value.editedNumberOfCourses,
                        editBaseEntryDialogBoxVisibility = false,
                        editedNumberOfCourses = _fiveSgpaUiState.value.editedNumberOfCourses


                    )
                }
            }

            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else {
            _fiveSgpaUiState.update {
                it.copy(
                    errorToastMessageVisibilityETNOCDB = false,
                    errorMessageHolderForETNOCDBToastMessage = "",
                    editBaseEntryDialogBoxVisibility = false,
                )
            }
        }


    }


    private fun textFieldsErrorCheckEditedCourseDataEntry() {

        if (_fiveSgpaUiState.value.courseCode.isEmpty()) {

            _fiveSgpaUiState.update {
                it.copy(
                    defaultEditCourseCodeLabel = FiveErrorMessages.errorMessageForCourseCode,
                    defaultLabelColourECC = FiveErrorMessages.textFieldErrorLabelColorHexCode


                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else {

            if (
                _courseEntries.value.contains(
                    FiveGpData(
                        courseCode = _fiveSgpaUiState.value.courseCode.uppercase(),
                        courseGrade = _fiveSgpaUiState.value.selectedCourseGrade,
                        courseUnit = _fiveSgpaUiState.value.selectedCourseUnit.toInt()
                    )
                )
            ) {

                _fiveSgpaUiState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString(),
                        courseEntryEditDialogBoxVisibility = false,

                        )
                }


            } else {
                _courseEntries.value[_fiveSgpaUiState.value.courseEntryIndex.toInt()] = FiveGpData(
                    courseCode = _fiveSgpaUiState.value.courseCode.uppercase(),
                    courseGrade = _fiveSgpaUiState.value.selectedCourseGrade,
                    courseUnit = _fiveSgpaUiState.value.selectedCourseUnit.toInt()
                )
                savedStateHandle.set(
                    FIVE_SGPA_COURSE_ENTRIES_KEY,
                    _courseEntries.value
                )


                _fiveSgpaUiState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString(),
                        courseEntryEditDialogBoxVisibility = false
//                    courseEntryDialogBoxVisibility = false,

                    )
                }
                clearCourseDataEntry()
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }


        }


    }


    private fun textFieldsErrorCheckCourseDataEntry() {

        if (_fiveSgpaUiState.value.courseCode.isEmpty()) {

            _fiveSgpaUiState.update {
                it.copy(
                    defaultEnteredCourseCodeLabel = FiveErrorMessages.errorMessageForCourseCode,
                    defaultLabelColourCC = FiveErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else if (_fiveSgpaUiState.value.selectedCourseUnit.isEmpty()) {
            _fiveSgpaUiState.update {
                it.copy(
                    pickedCourseUnitDefaultLabel = FiveErrorMessages.errorMessageForCourseUnit,
                    defaultLabelColourCU = FiveErrorMessages.textFieldErrorLabelColorHexCode

                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else if (_fiveSgpaUiState.value.selectedCourseGrade.isEmpty()) {

            _fiveSgpaUiState.update {
                it.copy(
                    pickedCourseGradeDefaultLabel = FiveErrorMessages.errorMessageForCourseGrade,
                    defaultLabelColourCG = FiveErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else if (


            _fiveSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.contains(
                _fiveSgpaUiState.value.courseCode.uppercase(Locale.UK)
            )
        ) {


            _fiveSgpaUiState.update {
                it.copy(
                    matchAlreadyInCourseEntry = _fiveSgpaUiState.value.courseCode.uppercase(Locale.UK),
                    allReadyInList = true,

                    )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)
            _fiveSgpaUiState.update {
                it.copy(
                    // allReadyInList = false,
                    //matchAlreadyInCourseEntry = ""
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


        } else {

            _courseEntries.value.add(
                FiveGpData(
                    _fiveSgpaUiState.value.courseCode.uppercase(Locale.UK),
                    _fiveSgpaUiState.value.selectedCourseGrade,
                    _fiveSgpaUiState.value.selectedCourseUnit.toInt()
                )
            )
            savedStateHandle.set(
                FIVE_SGPA_COURSE_ENTRIES_KEY,
                _courseEntries.value
            )


            _fiveSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.add(
                _fiveSgpaUiState.value.courseCode.uppercase(
                    Locale.UK
                )
            )
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)



            _fiveSgpaUiState.update {
                it.copy(
                    allReadyInList = false,
                    enteredCourses = _courseEntries.value.size.toString(),
                    courseEntryDialogBoxVisibility = false,
                    //matchAlreadyInCourseEntry =

                )
            }

            if (_fiveSgpaUiState.value.totalCourses == _fiveSgpaUiState.value.enteredCourses) {
                _fiveSgpaUiState.update {
                    it.copy(
                        changeDoneIcon = true
                    )
                }


            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            clearCourseDataEntry()


        }


    }


    private fun textFieldsErrorCheckSaveFiveCgpaResultAsDataEntry() {
        if (
            _fiveCgpaUiState.value.saveResultAs.isEmpty()
        ) {
            _fiveCgpaUiState.update {
                it.copy(
                    defaultLabelSRA = FiveErrorMessages.errorMessageForCgpaSRA,
                    defaultLabelColourSRA = FiveErrorMessages.textFieldErrorLabelColorHexCode,
                    saveResultDBVisibilty = true
                )
            }
            savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

        } else if (_fiveCgpaUiState.value.saveResultAs.isNotEmpty()) {
            viewModelScope.launch {
                myFiveCgpaRepository.InsertFiveCgpaResult(
                    FiveCgpaResultEntity(
                        resultName = _fiveCgpaUiState.value.saveResultAs.uppercase(),
                        gp = _fiveCgpaUiState.value.cgpa,
                        remark = _fiveCgpaUiState.value.remark,
                        resultEntries = _fiveCgpaUiState.value.sgpaResultNames,
                        resultGpaDescriptor = _fiveCgpaUiState.value.gpaDescriptor

                    )
                )
            }
            _fiveCgpaUiState.update {
                it.copy(
                    defaultLabelColourSRA = FiveErrorPassedValues.errorPassedColour,
                    defaultLabelSRA = FiveErrorPassedValues.labelForSRA,
                    saveResultDBVisibilty = false,
                    saveResultAs = ""
                )
            }
            savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            Log.d("names", "${_fiveCgpaUiState.value.sgpaResultNames}")


        }


        // savedStateHandle.set(FiveGpaViewModel.FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


    }

    private fun resetFiveCgpaSRADBox() {
        _fiveCgpaUiState.update {
            it.copy(
                defaultLabelSRA = FiveErrorPassedValues.labelForSRA,
                defaultLabelColourSRA = FiveErrorPassedValues.errorPassedColour,
                // saveResultDBVisibilty = false,
                //saveResultAs = ""


            )
        }
        savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
    }


    private fun textFieldsErrorCheckAndDuplicateEntrySaveFiveSgpaResultAsDataEntry(
        resultNameForCheck: String
    ) {
        if (_fiveSgpaUiState.value.saveResultAs.isEmpty()) {
            _fiveSgpaUiState.update {
                it.copy(
                    defaultLabelSRA = FiveErrorMessages.errorMessageForSRA,
                    defaultLabelColourSRA = FiveErrorMessages.textFieldErrorLabelColorHexCode,
                    saveResultAsDialogBoxVisibility = true
                )
            }

        } else if (_fiveSgpaUiState.value.saveResultAs.isNotEmpty()) {
//            for (i in 0 until _fiveSgparesultIntroDB.value.resultItems.size) {
//                if (_fiveSgparesultIntroDB.value.resultItems[i].resultName.uppercase() == resultNameForCheck.uppercase()) {
//                    Log.d(
//                        "duplicate name",
//                        "the  name ${_fiveSgparesultIntroDB.value.resultItems[i].resultName} is repeating"
//                    )
//                    _fiveSgpaUiState.update {
//                        it.copy(
//                            defaultLabelSRA = FiveErrorMessages.errorDuplicateNameForSRA,
//                            defaultLabelColourSRA = FiveErrorMessages.textFieldErrorLabelColorHexCode,
//                            saveResultAsDialogBoxVisibility = true
//                        )
//                    }                 //
//
//                }
//
//
//            }


//            _fiveSgpaUiState.update {
//                it.copy(
//                    fiveSgpaSRAToastNotifier = true
//                )
//
//            }

            //Toast.makeText(getActivity())
            viewModelScope.launch {
                val execTime = measureTimeMillis {
                    myFiveSgpaRepository.InsertFiveSgpaResult(
                        FiveSgpaResultEntity(
                            resultEntries = _courseEntries.value,
                            gp = _fiveSgpaUiState.value.fiveSgpaFinalResult,
                            resultName = _fiveSgpaUiState.value.saveResultAs.uppercase(),
                            remark = _fiveSgpaUiState.value.remark,
                            resultGpaDescriptor = _fiveSgpaUiState.value.gpaDescriptor

                        )
                    )
                    _fiveCgpaUiState.value.displayedResultForFiveCgpaCalculation.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.cgpaList.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)
                    _fiveCgpaUiState.value.sgpaListToBeCalculated.clear()
                    savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

                }
                Log.d("ViewModel", "Exec time: ${execTime}")
                delay(1000)
                _fiveSgpaUiState.update {
                    it.copy(
                        fiveSgpaSRAToastNotifier = true
                    )

                }

                // delay(100)

//                _fiveSgpaUiState.update {
//                    it.copy(
//                        fiveSgpaSRAToastNotifier = false
//                    )
//
//                }

            }
            _fiveSgpaUiState.update {
                it.copy(
                    // fiveSgpaSRAToastNotifier = false,
                    saveResultAsDialogBoxVisibility = false,
                    saveResultAs = ""
                )
            }

//            viewModelScope.launch {
//
//                delay(1000)
//                _fiveSgpaUiState.update {
//                    it.copy(
//                        fiveSgpaSRAToastNotifier = false,
//                        // saveResultAsDialogBoxVisibility = false,
//                    )
//                }
//
//            }
            resetFiveSgpaSRADBox()


        }




        savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


    }


    private fun resetFiveSgpaSRADBox() {
        _fiveSgpaUiState.update {
            it.copy(
                defaultLabelSRA = FiveErrorPassedValues.labelForSRA,
                defaultLabelColourSRA = FiveErrorPassedValues.errorPassedColour,
                //saveResultAsDialogBoxVisibility = false,
                //saveResultAs = ""


            )
        }
        savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)
    }

    private fun operations(totalCreditLoad: Int): String {

        coursesDataEntryObj.sixUnitCoursesPointSum =
            coursePointObj.sixUnitA.sum() + coursePointObj.sixUnitB.sum() + coursePointObj.sixUnitC.sum() + coursePointObj.sixUnitD.sum() + coursePointObj.sixUnitE.sum() + coursePointObj.sixUnitF.sum()
        coursesDataEntryObj.fourUnitCoursesPointSum =
            coursePointObj.fourUnitA.sum() + coursePointObj.fourUnitB.sum() + coursePointObj.fourUnitC.sum() + coursePointObj.fourUnitD.sum() + coursePointObj.fourUnitE.sum() + coursePointObj.fourUnitF.sum()
        coursesDataEntryObj.threeUnitCoursesPointSum =
            coursePointObj.threeUnitA.sum() + coursePointObj.threeUnitB.sum() + coursePointObj.threeUnitC.sum() + coursePointObj.threeUnitD.sum() + coursePointObj.threeUnitE.sum() + coursePointObj.threeUnitF.sum()
        coursesDataEntryObj.twoUnitCoursesPointSum =
            coursePointObj.twoUnitA.sum() + coursePointObj.twoUnitB.sum() + coursePointObj.twoUnitC.sum() + coursePointObj.twoUnitD.sum() + coursePointObj.twoUnitE.sum() + coursePointObj.twoUnitF.sum()
        coursesDataEntryObj.oneUnitCoursesPointSum =
            coursePointObj.oneUnitA.sum() + coursePointObj.oneUnitB.sum() + coursePointObj.oneUnitC.sum() + coursePointObj.oneUnitD.sum() + coursePointObj.oneUnitE.sum() + coursePointObj.oneUnitF.sum()
        coursesDataEntryObj.totalCoursesPointSum =
            (coursesDataEntryObj.sixUnitCoursesPointSum + coursesDataEntryObj.fourUnitCoursesPointSum + coursesDataEntryObj.threeUnitCoursesPointSum + coursesDataEntryObj.twoUnitCoursesPointSum + coursesDataEntryObj.oneUnitCoursesPointSum).toDouble()


        var finalAns = (coursesDataEntryObj.totalCoursesPointSum / totalCreditLoad)
        //var decimalFormat = DecimalFormat("#.##")
        var final_result = String.format("%.2f", finalAns)


        return ("$final_result")
    }

    private fun courseValueMapper(courseGrade: ArrayList<FiveGpData>) {


        courseGrade.forEach { courseData ->

            when (courseData) {

                //For six unit Courses

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["A"]?.let { coursePointObj.sixUnitA.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["B"]?.let { coursePointObj.sixUnitB.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["C"]?.let { coursePointObj.sixUnitC.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["D"]?.let { coursePointObj.sixUnitD.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["E"]?.let { coursePointObj.sixUnitE.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["F"]?.let { coursePointObj.sixUnitF.add(it) }

                }


                //For four unit Courses

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["A"]?.let { coursePointObj.fourUnitA.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["B"]?.let { coursePointObj.fourUnitB.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["C"]?.let { coursePointObj.fourUnitC.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["D"]?.let { coursePointObj.fourUnitD.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["E"]?.let { coursePointObj.fourUnitE.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["F"]?.let { coursePointObj.fourUnitF.add(it) }

                }


                //For three unit Courses

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["A"]?.let { coursePointObj.threeUnitA.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["B"]?.let { coursePointObj.threeUnitB.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["C"]?.let { coursePointObj.threeUnitC.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["D"]?.let { coursePointObj.threeUnitD.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["E"]?.let { coursePointObj.threeUnitE.add(it) }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["F"]?.let { coursePointObj.threeUnitF.add(it) }

                }


                //For two unit courses:

                FiveGpData(courseCode = courseData.courseCode, "A", 2) -> {

                    courseMapObj.twoUnit_GradeMap["A"]?.let {

                        coursePointObj.twoUnitA.add(it)
                    }

                }

                FiveGpData(courseCode = courseData.courseCode, "B", 2) -> {

                    courseMapObj.twoUnit_GradeMap["B"]?.let {

                        coursePointObj.twoUnitB.add(it)
                    }

                }

                FiveGpData(courseCode = courseData.courseCode, "C", 2) -> {

                    courseMapObj.twoUnit_GradeMap["C"]?.let {

                        coursePointObj.twoUnitC.add(it)
                    }

                }

                FiveGpData(courseCode = courseData.courseCode, "D", 2) -> {

                    courseMapObj.twoUnit_GradeMap["D"]?.let {

                        coursePointObj.twoUnitD.add(it)
                    }

                }

                FiveGpData(courseCode = courseData.courseCode, "E", 2) -> {

                    courseMapObj.twoUnit_GradeMap["E"]?.let {

                        coursePointObj.twoUnitE.add(it)
                    }

                }

                FiveGpData(courseCode = courseData.courseCode, "A", 2) -> {

                    courseMapObj.twoUnit_GradeMap["F"]?.let {

                        coursePointObj.twoUnitF.add(it)
                    }

                }

                //For one unit Courses

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["A"]?.let {
                        coursePointObj.oneUnitA.add(it)
                    }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["B"]?.let {
                        coursePointObj.oneUnitB.add(it)
                    }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["C"]?.let {
                        coursePointObj.oneUnitC.add(it)
                    }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["D"]?.let {
                        coursePointObj.oneUnitD.add(it)
                    }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["E"]?.let {
                        coursePointObj.oneUnitE.add(it)
                    }

                }

                FiveGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["F"]?.let {
                        coursePointObj.oneUnitF.add(it)
                    }

                }


            }


        }

    }

    private fun clearCourseDataEntry() {
        _fiveSgpaUiState.update {
            it.copy(
                courseCode = "",
                selectedCourseUnit = "",
                selectedCourseGrade = "",
                pickedCourseGradeDefaultLabel = FiveErrorPassedValues.enterCourseGradeLabel,
                pickedCourseUnitDefaultLabel = FiveErrorPassedValues.enterCourseUnitLabel,
                //defaultEnteredCourseCodeLabel = FiveErrorPassedValues.enterCourseCodeLabel,

            )

        }
        savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

    }

    private fun onReExecuteCalculationClearArrayField() {

        coursePointObj.sixUnitA.clear()
        coursePointObj.sixUnitB.clear()
        coursePointObj.sixUnitC.clear()
        coursePointObj.sixUnitD.clear()
        coursePointObj.sixUnitE.clear()
        coursePointObj.sixUnitF.clear()

        coursePointObj.fourUnitA.clear()
        coursePointObj.fourUnitB.clear()
        coursePointObj.fourUnitC.clear()
        coursePointObj.fourUnitD.clear()
        coursePointObj.fourUnitE.clear()
        coursePointObj.fourUnitF.clear()

        coursePointObj.threeUnitA.clear()
        coursePointObj.threeUnitB.clear()
        coursePointObj.threeUnitC.clear()
        coursePointObj.threeUnitD.clear()
        coursePointObj.threeUnitE.clear()
        coursePointObj.threeUnitF.clear()


        coursePointObj.twoUnitA.clear()
        coursePointObj.twoUnitB.clear()
        coursePointObj.twoUnitC.clear()
        coursePointObj.twoUnitD.clear()
        coursePointObj.twoUnitE.clear()
        coursePointObj.twoUnitF.clear()

        coursePointObj.oneUnitA.clear()
        coursePointObj.oneUnitB.clear()
        coursePointObj.oneUnitC.clear()
        coursePointObj.oneUnitD.clear()
        coursePointObj.oneUnitE.clear()
        coursePointObj.oneUnitF.clear()
        coursesUnitSubList.clear()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun greetings() {


        var myCurrentTime = LocalDateTime.now()
        var timeFormatter = ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        var formattedTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            myCurrentTime.format(timeFormatter).toString()
        } else {
            TODO("VERSION.SDK_INT < O")

        }
        var hour = formattedTime[11].toString()
        var realHour = formattedTime[12].toString()
        var separator = formattedTime[13].toString()
        var min = formattedTime[14].toString()
        var realMin = formattedTime[15].toString()


        var myHour = hour + realHour
        var morning =
            listOf<String>("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11")
        var afternoon = listOf<String>("12", "13", "14", "15")
        var evening = listOf<String>("16", "17", "18")
        var night = listOf<String>("19", "20", "21", "22", "23")



        if (morning.contains(myHour)) {
            _fiveSgpaUiState.update {
                it.copy(
                    greeting = "Good Morning"
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            println("Good Morning")
        } else if (afternoon.contains(myHour)) {
            _fiveSgpaUiState.update {
                it.copy(
                    greeting = "Good Afternoon"
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            println("Good Afternoon")

        } else if (evening.contains(myHour)) {

            _fiveSgpaUiState.update {
                it.copy(
                    greeting = "Good Evening"
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            println("Good Evening")

        } else if (night.contains(myHour)) {

            _fiveSgpaUiState.update {
                it.copy(
                    greeting = "Good Evening"
                )
            }
            savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            println("Good Night")

        }
    }

    private fun GpaDescriptor(gpa: Float, desc: String) {
        if (desc == "sgpa") {
            if (gpa in 4.50..5.00) {
                _fiveSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "First Class",
                        remark = "You Performed Brilliantly"
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)

            } else if (gpa in 3.50..4.49) {

                _fiveSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Second Class Upper",
                        remark = "You Performed Amazing "
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            } else if (gpa in 2.40..3.49) {

                _fiveSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Second Class Lower",
                        remark = "You Performed Great"
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            } else if (gpa in 1.50..2.39) {

                _fiveSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Third Class",
                        remark = "You performed averagely"
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            } else if (gpa in 1.00..1.49) {

                _fiveSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Pass",
                        remark = "You passed"
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            } else if (gpa in 0.00..1.00) {

                _fiveSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Failure",
                        remark = "You Failed"
                    )
                }
                savedStateHandle.set(FIVE_SGPA_UI_STATE_KEY, _fiveSgpaUiState.value)


            }

        } else if (desc == "cgpa") {

            if (gpa in 4.50..5.00) {
                _fiveCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "First Class",
                        remark = "You Performed Brilliantly"
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)

            } else if (gpa in 3.50..4.49) {

                _fiveCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Second Class Upper",
                        remark = "You Performed Amazing "
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            } else if (gpa in 2.40..3.49) {

                _fiveCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Second Class Lower",
                        remark = "You Performed Great"
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            } else if (gpa in 1.50..2.39) {

                _fiveCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Third Class",
                        remark = "You performed averagely"
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            } else if (gpa in 1.00..1.49) {

                _fiveCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Pass",
                        remark = "You passed"
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            } else if (gpa in 0.00..1.00) {

                _fiveCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Failure",
                        remark = "You Failed"
                    )
                }
                savedStateHandle.set(FIVE_CGPA_UI_STATE_KEY, _fiveCgpaUiState.value)


            }


        }

    }


}