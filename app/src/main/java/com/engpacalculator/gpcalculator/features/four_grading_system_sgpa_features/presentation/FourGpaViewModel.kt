package com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation

import GpCalculatorPrototype.Data.FourCourseDataEntries
import GpCalculatorPrototype.Data.FourCourseMaps
import GpCalculatorPrototype.Data.FourCoursesUnitPointArrayList
import GpCalculatorPrototype.Data.FourGpData
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.domain.repository.FourSgpaResultRepository
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.FourErrorMessages
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.FourErrorPassedValues
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FourSgpaResultsRecordState
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.ResultTracker
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFourCgpaCalculation
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.domain.repository.FourCgpaResultRepository
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.FourCgpaUiStates
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component.FourCgpaResultsRecordState
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
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
class FourGpaViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val myFourSgpaRepository: FourSgpaResultRepository,
    private val myFourCgpaRepository: FourCgpaResultRepository,


    ) : ViewModel() {

    companion object {
        private const val FOUR_SGPA_COURSE_ENTRIES_KEY = "my_four_sgpa_course_entry_state"
        private const val FOUR_SGPA_UI_STATE_KEY = "my_four_sgpa_ui_state"
        private const val FOUR_SGPA_RESULT_INTRO_DBASE = "my_four_sgpa_result_intro_data_base"
        private const val FOUR_CGPA_UI_STATE_KEY = "my_four_cgpa_ui_state"
        private const val FOUR_CGPA_RESULT_INTRO_DBASE = "my_four_Cgpa_result_intro_data_base"
    }

    private val coursePointObj = FourCoursesUnitPointArrayList()
    private val courseMapObj = FourCourseMaps()
    private val coursesDataEntryObj = FourCourseDataEntries()
    private val stateClassObject = FourSgpaUiStates()
    private var result = ""
    var coursesUnitSubList = ArrayList<Int>()


    private var _courseEntries = MutableStateFlow(
        savedStateHandle.get(FOUR_SGPA_COURSE_ENTRIES_KEY)
            ?: FourCourseDataEntries().coursesDataEntry
    )
    var courseEntries = _courseEntries.asStateFlow()

    private var _fourSgpaUiState =
        MutableStateFlow(savedStateHandle.get(FOUR_SGPA_UI_STATE_KEY) ?: FourSgpaUiStates())
    var fourSgpaUiState = _fourSgpaUiState.asStateFlow()


    private var _fourSgparesultIntroDB = MutableStateFlow(
        savedStateHandle.get(
            FOUR_SGPA_RESULT_INTRO_DBASE
        ) ?: FourSgpaResultsRecordState()
    )
    val fourSgparesultIntroDB = _fourSgparesultIntroDB.asStateFlow()

    private var _fourCgpaResultIntroDB = MutableStateFlow(
        savedStateHandle.get(
            FOUR_CGPA_RESULT_INTRO_DBASE
        ) ?: FourCgpaResultsRecordState()
    )
    val fourCgpaResultIntroDB = _fourCgpaResultIntroDB.asStateFlow()

    private var _fourCgpaUiState =
        MutableStateFlow(savedStateHandle.get(FOUR_CGPA_UI_STATE_KEY) ?: FourCgpaUiStates())
    var fourCgpaUiState = _fourCgpaUiState.asStateFlow()


    init {
        loadFourSgpaData()
        loadFourCgpaData()
    }

    private fun loadFourCgpaData(chkBoxState: Boolean = false, pseudoIndex: Int = 0) {
        viewModelScope.launch {

            myFourCgpaRepository.GetFourCgpaResultRecordDao()
                .collect { result ->

                    _fourCgpaResultIntroDB.update {
                        it.copy(
                            resultItems = result
                        )
                    }
                    savedStateHandle.set(FOUR_CGPA_RESULT_INTRO_DBASE, _fourCgpaResultIntroDB.value)

                    //  _resultIntroDB.value.resultItems = result

                }


        }

    }

    private fun loadFourSgpaData(chkBoxState: Boolean = false, pseudoIndex: Int = 0) {
        viewModelScope.launch {
            _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
            savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            myFourSgpaRepository.GetFourSgpaResultRecordDao()
                .collect { result ->

                    _fourSgparesultIntroDB.update {
                        it.copy(
                            resultItems = result
                        )
                    }
                    savedStateHandle.set(FOUR_SGPA_RESULT_INTRO_DBASE, _fourSgparesultIntroDB.value)


                    for (i in 0 until result.size) {

                        _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.add(
                            SgpaResultDisplayFormatForFourCgpaCalculation(
                                resultSelected = false,
                                resultName = result.get(i).resultName,
                                resultSgpa = result.get(i).gp
                            )
                        )
                        savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                    }
                    //  _resultIntroDB.value.resultItems = result

                }


        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: FourGpaUiEvents) {

        when (event) {
            // var TAG: String = "list"


            is FourGpaUiEvents.showFourCgpaSaveResultDB -> {
                _fourCgpaUiState.update {
                    it.copy(
                        saveResultDBVisibilty = true
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            }

            is FourGpaUiEvents.hideFourCgpaSaveResultDB -> {
                _fourCgpaUiState.update {
                    it.copy(
                        saveResultDBVisibilty = false,
                        saveResultAs = "",
                        defaultLabelColourSRA = FourErrorPassedValues.errorPassedColour,
                        defaultLabelSRA = FourErrorPassedValues.labelForSRA,

                        )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

            }

            is FourGpaUiEvents.helpFourCgpa -> {

                _fourCgpaUiState.update {
                    it.copy(
                        newHelperText = "Ahh..."
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

            }

            is FourGpaUiEvents.setFourCgpaSRA -> {
                _fourCgpaUiState.update {
                    it.copy(
                        saveResultAs = event.saveResultAs
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            }

            is FourGpaUiEvents.saveFourCgpaResult -> {


                textFieldsErrorCheckSaveFourCgpaResultAsDataEntry()


            }


            is FourGpaUiEvents.executeCgpaCalculation -> {
                if (_fourCgpaUiState.value.sgpaListToBeCalculated.isNotEmpty()) {
                    for (i in 0.._fourCgpaUiState.value.sgpaListToBeCalculated.size - 1) {
                        _fourCgpaUiState.value.cgpaList.add(
                            _fourCgpaUiState.value.sgpaListToBeCalculated.get(
                                i
                            ).sgpaResult.toDouble().toFloat()
                        )
                        savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

                    }

                    _fourCgpaUiState.update {
                        it.copy(
                            cgpa = String.format(
                                "%.2f",
                                _fourCgpaUiState.value.cgpaList.sum() / _fourCgpaUiState.value.cgpaList.size
                            )
                        )
                    }
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                    GpaDescriptor(_fourCgpaUiState.value.cgpa.toFloat(), "cgpa")
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                }

                Log.d(
                    "CGPA",
                    "Your sum is ${_fourCgpaUiState.value.cgpaList.sum()} and size is ${_fourCgpaUiState.value.cgpaList.size}"
                )
                Log.d("CGPA", "Your cgpa is ${_fourCgpaUiState.value.cgpa}")

                //_fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                _fourCgpaUiState.value.cgpaList.clear()
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

                //_fourCgpaUiState.value.sgpaListToBeCalculated.clear()
                //loadData()
            }

            is FourGpaUiEvents.onCheckChanged -> {


                _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.get(event.index).resultSelected =
                    event.isChecked
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                for (i in 0.._fourCgpaUiState.value.displayedResultForFourCgpaCalculation.size - 1) {
                    if (_fourCgpaUiState.value.displayedResultForFourCgpaCalculation[i].resultSelected) {
                        _fourCgpaUiState.update {
                            it.copy(operatorIconState = true)

                        }
                        savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

                        Log.d(
                            "StatusIconCheck",
                            "Your status are ${_fourCgpaUiState.value.displayedResultForFourCgpaCalculation}"
                        )

                    } else if (_fourCgpaUiState.value.displayedResultForFourCgpaCalculation[i].resultSelected == false && event.isChecked == false) {
                        _fourCgpaUiState.update {
                            it.copy(operatorIconState = false)
                        }
                        savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

                    }

                    "Your status are ${_fourCgpaUiState.value.displayedResultForFourCgpaCalculation}"


                }

                val randomNumber = Random.nextInt(1, 1000)
                _fourCgpaUiState.update {
                    it.copy(
                        helperText = randomNumber.toString()
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)



                if (event.isChecked == true) {
                    _fourCgpaUiState.update {
                        it.copy(operatorIconState = true)
                    }
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                    _fourCgpaUiState.value.sgpaListToBeCalculated.add(
                        //index = event.index,
                        ResultTracker(
                            id = event.index,
                            sgpaResult = event.sgpaNeeded,
                            resultName = event.resultNameRef
                        )
                    )
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.sgpaResultNames.add(event.resultNameRef)
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

                } else {
                    _fourCgpaUiState.value.sgpaListToBeCalculated.removeIf {
                        it.id == event.index
                    }
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.sgpaResultNames.remove(event.resultNameRef)
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                }

                // println(_fourCgpaUiState.value.sgpaListToBeCalculated)
                Log.d("List of sgpa", "${_fourCgpaUiState.value.sgpaListToBeCalculated}")
            }

            is FourGpaUiEvents.DeleteFourGpaResultByReference -> {
                _fourCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                viewModelScope.launch {
                    myFourSgpaRepository.FourSgpaResultToBeDeleted(event.fourSgpaResultName)
                    _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.cgpaList.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                }
            }

            is FourGpaUiEvents.DeleteFourCgpaResultByReference -> {
                _fourCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                viewModelScope.launch {
                    myFourCgpaRepository.FourCgpaResultToBeDeleted(event.fourCgpaResultName)
//                    _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
//                    _fourCgpaUiState.value.cgpaList.clear()
//                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()

                }
            }


            is FourGpaUiEvents.DeleteResult -> {
                _fourCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                viewModelScope.launch {
                    myFourSgpaRepository.DeleteFourSgpaResult(event.fourSgpaResultName)
                    _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.cgpaList.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


                }

            }

            is FourGpaUiEvents.showResultDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        resultDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideFourSgpaSaveResultDB -> {
                _fourSgpaUiState.update {
                    it.copy(
                        saveResultAsDialogBoxVisibility = false,
                        saveResultAs = ""

                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.setSRA -> {
                _fourSgpaUiState.update {
                    it.copy(
                        saveResultAs = event.savedResultName

                    )
                }

            }

            is FourGpaUiEvents.saveFourSgpaResult -> {

//                _fourSgpaUiState.update {
//                    it.copy(
//                        FourSgpaSRAToastNotifier = true
//                    )
//                }
                textFieldsErrorCheckAndDuplicateEntrySaveFourSgpaResultAsDataEntry(_fourSgpaUiState.value.saveResultAs)


            }

            is FourGpaUiEvents.resetBackToDefaultValueFromErrorSRA -> {
                resetFourSgpaSRADBox()

            }

            is FourGpaUiEvents.resetBackToDefaultValueFromErrorSRAFourCgpa -> {
                resetFourCgpaSRADBox()

            }

            is FourGpaUiEvents.replaceEditedInEntriesToArrayList -> {
                textFieldsErrorCheckEditedCourseDataEntry()
            }

            is FourGpaUiEvents.editItemsEntries -> {

                _fourSgpaUiState.update {
                    it.copy(
                        courseCode = event.courseCodeEdit,
                        selectedCourseUnit = event.unitEdit,
                        selectedCourseGrade = event.gradeEdit

                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.updateCourseIndexEntry -> {
                _fourSgpaUiState.update {
                    it.copy(
                        courseEntryIndex = event.entryIndex
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showCourseEntryEditDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = true,
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideCourseEntryEditDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }


            is FourGpaUiEvents.resetResultField -> {

                _fourSgpaUiState.update {
                    it.copy(
                        fourSgpaFinalResult = "new val"
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showDataEntryDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        courseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

            is FourGpaUiEvents.hideDataEntryDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        allReadyInList = false,
                        courseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showUnitMenuDropDown -> {

                _fourSgpaUiState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

            is FourGpaUiEvents.hideUnitMenuDropDown -> {

                _fourSgpaUiState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

            is FourGpaUiEvents.showGradeMenuDropDown -> {
                _fourSgpaUiState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideGradeMenuDropDown -> {
                _fourSgpaUiState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.setSelectedCourseGrade -> {

                _fourSgpaUiState.update {
                    it.copy(
                        selectedCourseGrade = event.grade
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

            is FourGpaUiEvents.setSelectedCourseUnit -> {
                _fourSgpaUiState.update {
                    it.copy(
                        selectedCourseUnit = event.unit
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.deleteCourseEntry -> {

                _courseEntries.value.removeAt(event.itemToRemove)
                savedStateHandle.set(FOUR_SGPA_COURSE_ENTRIES_KEY, _courseEntries.value)
                _fourSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.removeAt(event.itemToRemove)
                _fourSgpaUiState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString()
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.setCourseCode -> {

                if (_fourSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.contains(event.courseCode.uppercase())) {
                    _fourSgpaUiState.update {
                        it.copy(
                            allReadyInList = true,
                            matchAlreadyInCourseEntry = event.courseCode.uppercase()
                        )
                    }
                } else {
                    _fourSgpaUiState.update {
                        it.copy(
                            allReadyInList = false,
                            matchAlreadyInCourseEntry = ""

                        )
                    }

                }
                _fourSgpaUiState.update {
                    it.copy(
                        courseCode = event.courseCode.replace(" ", "")
                    )


                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

//                } else {
//                    _fourSgpaUiState.update {
//                        it.copy(
//                            courseCode = event.courseCode
//                        )
//
//
//                    }
//                }


            }

            is FourGpaUiEvents.addEntriesToArrayList -> {

                textFieldsErrorCheckCourseDataEntry()


            }

            is FourGpaUiEvents.setTotalCreditLoad -> {
                _fourSgpaUiState.update {
                    it.copy(
                        totalCreditLoad = event.totalCreditLoad
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.setTotalCourses -> {
//                var use = event.totalCourses
//                if (use[0] == '0') {
//                    use = use.replace("0", "")
//                    _fourSgpaUiState.update {
//                        it.copy(
//                            totalCourses = use
//                        )
//                    }
//                    savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)
//
//
//                } else if (use[0] == '0' && use[1] == '0') {
//                    use = use.replace("0", "")
//                    _fourSgpaUiState.update {
//                        it.copy(
//                            totalCourses = use
//                        )
//                    }
//                    savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)
//
//
//                } else {
                _fourSgpaUiState.update {
                    it.copy(
                        totalCourses = event.totalCourses
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


                // }


            }

            is FourGpaUiEvents.hideBaseEntryDBox -> {
                textFieldsErrorCheckBaseEntryDB()

            }

            is FourGpaUiEvents.showSaveResultDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        saveResultAsDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

//            is FourGpaUiEvents.hideFourSgpaSaveResultDB -> {
//                _fourSgpaUiState.update {
//
//                    it.copy(
//                        saveResultAsDialogBoxVisibility = false,
//                    )
//                }
//                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)
//
//            }

            is FourGpaUiEvents.resetAlreadyInList -> {
                _fourSgpaUiState.update {
                    it.copy(
                        allReadyInList = false
                    )

                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

            is FourGpaUiEvents.showCourseDataEntriesContextmenu -> {

                _fourSgpaUiState.update {
                    it.copy(
                        courseItemsDropDownVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

            is FourGpaUiEvents.hideCourseDataEntriesContextmenu -> {
                _fourSgpaUiState.update {
                    it.copy(
                        courseItemsDropDownVisibility = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.resetTotalEntries -> {


                if (_fourSgpaUiState.value.totalCourses > 0.toString()) {


                    _fourSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.clear()
                    _courseEntries.value.clear()
                    _fourSgpaUiState.update {
                        it.copy(
                            //totalCourses = "",
                            totalCreditLoad = "",
                            enteredCourses = "0",

                            //baseEntryDialogBoxVisibility = true
                        )
                    }
                    savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


                } else {


                }


            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC -> {
                _fourSgpaUiState.update {
                    it.copy(
                        defaultLabelColourTNOC = FourErrorPassedValues.errorPassedColour,
                        defaultLabelTNOC = FourErrorPassedValues.labelForTNOC

                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCC -> {
                _fourSgpaUiState.update {
                    it.copy(
                        defaultLabelColourCC = FourErrorPassedValues.errorPassedColour,
                        defaultEnteredCourseCodeLabel = FourErrorPassedValues.enterCourseCodeLabel

                    )
                }

            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsECC -> {
                _fourSgpaUiState.update {
                    it.copy(
                        defaultLabelColourECC = FourErrorPassedValues.errorPassedColour,
                        defaultEditCourseCodeLabel = FourErrorPassedValues.editCourseCodeLabel

                    )
                }

            }


            is FourGpaUiEvents.resetDefaultValuesFromErrorsTNOCL -> {
                _fourSgpaUiState.update {
                    it.copy(
                        defaultLabelTNOCL = FourErrorPassedValues.labelForTNOCC,
                        //defaultLabelColourTNOCL = FourErrorPassedValues.errorPassedColour

                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showEditBaseEntryDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        editBaseEntryDialogBoxVisibility = true,
                        errorToastMessageVisibilityETNOCDB = true

                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideEditBaseEntryDBox -> {

                textFieldsErrorEditedCheckBaseEntryDB()

                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideEditBaseEntryRegardlessDBox -> {

                if (_fourSgpaUiState.value.enteredCourses == "0") {

                    _fourSgpaUiState.update {
                        it.copy(
                            editBaseEntryDialogBoxVisibility = false,
                            totalCourses = _fourSgpaUiState.value.prevTotalNumberOfCourses
                        )
                    }

                    savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

                } else if (_fourSgpaUiState.value.enteredCourses != "0") {

                    _fourSgpaUiState.update {
                        it.copy(
                            editBaseEntryDialogBoxVisibility = false,
                            totalCourses = _fourSgpaUiState.value.enteredCourses

                        )
                    }

                    savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


                }


            }


            is FourGpaUiEvents.showBaseEntryDBox -> {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    greetings()
                }

                _fourSgpaUiState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }


            is FourGpaUiEvents.executeCalculation -> {

                _fourSgpaUiState.update {
                    it.copy(
                        changeDoneIcon = true
                    )
                }

                var execTotalUnit: Int = 0


                for (i in 1.._fourSgpaUiState.value.totalCourses.toInt()) {
                    coursesUnitSubList.add(_courseEntries.value[i - 1].courseUnit)
                    savedStateHandle.set(FOUR_SGPA_COURSE_ENTRIES_KEY, _courseEntries.value)

                }
                execTotalUnit = coursesUnitSubList.sum()
                _fourSgpaUiState.update {
                    it.copy(
                        totalCreditLoad = execTotalUnit.toString()
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)



                courseValueMapper(_courseEntries.value)
                result = operations(_fourSgpaUiState.value.totalCreditLoad.toInt())
                _fourSgpaUiState.update {
                    it.copy(
                        fourSgpaFinalResult = result
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


                GpaDescriptor(_fourSgpaUiState.value.fourSgpaFinalResult.toFloat(), "sgpa")

                onReExecuteCalculationClearArrayField()


            }

            is FourGpaUiEvents.hideBaseEntryRegardlessDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = false,
                        totalCourses = "",
                        prevTotalNumberOfCourses = ""

                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.setPrevTotalCourses -> {
                _fourSgpaUiState.update {
                    it.copy(
                        prevTotalNumberOfCourses = event.text
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showClearConfirmationDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideClearConfirmationDBox -> {
                _fourSgpaUiState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showHomeAdShimmerEffect -> {
                _fourSgpaUiState.update {
                    it.copy(
                        homeAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideHomeAdShimmerEffect -> {
                _fourSgpaUiState.update {
                    it.copy(
                        homeAdShimmerEffectVisibility = false
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.showAboutAdShimmerEffect -> {
                _fourSgpaUiState.update {
                    it.copy(
                        aboutAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.hideAboutAdShimmerEffect -> {
                _fourSgpaUiState.update {
                    it.copy(
                        aboutAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            }

            is FourGpaUiEvents.setTotalNumberOfEditedCourses -> {
                _fourSgpaUiState.update {
                    it.copy(
                        editedNumberOfCourses = event.noOfEditedTotalCourse
                    )
                }
            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCU -> {
                _fourSgpaUiState.update {
                    it.copy(
                        pickedCourseUnitDefaultLabel = FourErrorPassedValues.enterCourseUnitLabel,
                        defaultLabelColourCU = FourErrorPassedValues.dropDownErrorPassedColour
                    )
                }
            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCG -> {
                _fourSgpaUiState.update {
                    it.copy(
                        pickedCourseGradeDefaultLabel = FourErrorPassedValues.enterCourseGradeLabel,
                        defaultLabelColourCG = FourErrorPassedValues.dropDownErrorPassedColour
                    )
                }
            }

            is FourGpaUiEvents.showFourCgpaIntroDialogBox -> {
                _fourCgpaUiState.update {
                    it.copy(
                        fourCgpaIntroDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

            }

            is FourGpaUiEvents.hideFourCgpaIntroDialogBox -> {
                _fourCgpaUiState.update {
                    it.copy(
                        fourCgpaIntroDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

            }


            else -> {}
        }

    }


    private fun textFieldsErrorCheckBaseEntryDB() {

        if (_fourSgpaUiState.value.totalCourses.isEmpty() || _fourSgpaUiState.value.totalCourses == "0" || _fourSgpaUiState.value.totalCourses == "00" || _fourSgpaUiState.value.totalCourses.get(
                0
            ) == '0'
        ) {

            _fourSgpaUiState.update {
                it.copy(
                    defaultLabelTNOC = FourErrorMessages.errorLabelMessageForTNOC,
                    defaultLabelColourTNOC = FourErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else if (_fourSgpaUiState.value.totalCreditLoad.isNotEmpty()) {
            _fourSgpaUiState.update {
                it.copy(
                    baseEntryDialogBoxVisibility = false,
                    courseEntryDialogBoxVisibility = true

                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        }


    }

    private fun textFieldsErrorEditedCheckBaseEntryDB() {

        if (_fourSgpaUiState.value.totalCourses == _fourSgpaUiState.value.prevTotalNumberOfCourses && _fourSgpaUiState.value.enteredCourses == "0") {
            _fourSgpaUiState.update {
                it.copy(
                    errorMessageHolderForETNOCDBToastMessage = "No changes made",
                    editBaseEntryDialogBoxVisibility = false,
                    errorToastMessageVisibilityETNOCDB = false,

                    )
            }
        } else if (_fourSgpaUiState.value.editedNumberOfCourses.isEmpty() || _fourSgpaUiState.value.editedNumberOfCourses == "0" || _fourSgpaUiState.value.editedNumberOfCourses == "00" || _fourSgpaUiState.value.editedNumberOfCourses.get(
                0
            ) == '0'
        ) {
            if (_fourSgpaUiState.value.enteredCourses == "0") {
                _fourSgpaUiState.update {
                    it.copy(
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be empty",
                        totalCourses = _fourSgpaUiState.value.prevTotalNumberOfCourses,
                        editedNumberOfCourses = _fourSgpaUiState.value.prevTotalNumberOfCourses,
                        errorToastMessageVisibilityETNOCDB = true,


                        )
                }
            } else if (_fourSgpaUiState.value.enteredCourses != "0") {
                _fourSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be empty",
                        totalCourses = _fourSgpaUiState.value.enteredCourses,
                        editedNumberOfCourses = _fourSgpaUiState.value.enteredCourses

                    )
                }
            }

            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else if (_fourSgpaUiState.value.editedNumberOfCourses == "0") {

            if (_fourSgpaUiState.value.enteredCourses == "0") {
                _fourSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be 0",
                        totalCourses = _fourSgpaUiState.value.prevTotalNumberOfCourses,
                        editedNumberOfCourses = _fourSgpaUiState.value.prevTotalNumberOfCourses

                    )
                }
            } else if (_fourSgpaUiState.value.enteredCourses != "0") {
                _fourSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be 0",
                        totalCourses = _fourSgpaUiState.value.enteredCourses,
                        editedNumberOfCourses = _fourSgpaUiState.value.enteredCourses

                    )
                }
            }

            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else if (_fourSgpaUiState.value.editedNumberOfCourses.isNotEmpty() && _fourSgpaUiState.value.enteredCourses != "0") {

            if (_fourSgpaUiState.value.editedNumberOfCourses.toInt() < fourSgpaUiState.value.enteredCourses.toInt()) {
                _fourSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be less than already entered courses",
                        totalCourses = _fourSgpaUiState.value.enteredCourses,
                        editedNumberOfCourses = _fourSgpaUiState.value.enteredCourses

                    )
                }
            } else if (_fourSgpaUiState.value.editedNumberOfCourses >= _fourSgpaUiState.value.enteredCourses) {
                _fourSgpaUiState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = false,
                        errorMessageHolderForETNOCDBToastMessage = "successfully updated",
                        totalCourses = _fourSgpaUiState.value.editedNumberOfCourses,
                        editBaseEntryDialogBoxVisibility = false,
                        editedNumberOfCourses = _fourSgpaUiState.value.editedNumberOfCourses


                    )
                }
            }

            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else {
            _fourSgpaUiState.update {
                it.copy(
                    errorToastMessageVisibilityETNOCDB = false,
                    errorMessageHolderForETNOCDBToastMessage = "",
                    editBaseEntryDialogBoxVisibility = false,
                )
            }
        }


    }


    private fun textFieldsErrorCheckEditedCourseDataEntry() {

        if (_fourSgpaUiState.value.courseCode.isEmpty()) {

            _fourSgpaUiState.update {
                it.copy(
                    defaultEditCourseCodeLabel = FourErrorMessages.errorMessageForCourseCode,
                    defaultLabelColourECC = FourErrorMessages.textFieldErrorLabelColorHexCode


                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else {

            if (
                _courseEntries.value.contains(
                    FourGpData(
                        courseCode = _fourSgpaUiState.value.courseCode.uppercase(),
                        courseGrade = _fourSgpaUiState.value.selectedCourseGrade,
                        courseUnit = _fourSgpaUiState.value.selectedCourseUnit.toInt()
                    )
                )
            ) {

                _fourSgpaUiState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString(),
                        courseEntryEditDialogBoxVisibility = false,

                        )
                }


            } else {
                _courseEntries.value[_fourSgpaUiState.value.courseEntryIndex.toInt()] = FourGpData(
                    courseCode = _fourSgpaUiState.value.courseCode.uppercase(),
                    courseGrade = _fourSgpaUiState.value.selectedCourseGrade,
                    courseUnit = _fourSgpaUiState.value.selectedCourseUnit.toInt()
                )
                savedStateHandle.set(FOUR_SGPA_COURSE_ENTRIES_KEY, _courseEntries.value)


                _fourSgpaUiState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString(),
                        courseEntryEditDialogBoxVisibility = false
//                    courseEntryDialogBoxVisibility = false,

                    )
                }
                clearCourseDataEntry()
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }


        }


    }


    private fun textFieldsErrorCheckCourseDataEntry() {

        if (_fourSgpaUiState.value.courseCode.isEmpty()) {

            _fourSgpaUiState.update {
                it.copy(
                    defaultEnteredCourseCodeLabel = FourErrorMessages.errorMessageForCourseCode,
                    defaultLabelColourCC = FourErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else if (_fourSgpaUiState.value.selectedCourseUnit.isEmpty()) {
            _fourSgpaUiState.update {
                it.copy(
                    pickedCourseUnitDefaultLabel = FourErrorMessages.errorMessageForCourseUnit,
                    defaultLabelColourCU = FourErrorMessages.textFieldErrorLabelColorHexCode

                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else if (_fourSgpaUiState.value.selectedCourseGrade.isEmpty()) {

            _fourSgpaUiState.update {
                it.copy(
                    pickedCourseGradeDefaultLabel = FourErrorMessages.errorMessageForCourseGrade,
                    defaultLabelColourCG = FourErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else if (


            _fourSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.contains(
                _fourSgpaUiState.value.courseCode.uppercase(Locale.UK)
            )
        ) {


            _fourSgpaUiState.update {
                it.copy(
                    matchAlreadyInCourseEntry = _fourSgpaUiState.value.courseCode.uppercase(Locale.UK),
                    allReadyInList = true,

                    )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)
            _fourSgpaUiState.update {
                it.copy(
                    // allReadyInList = false,
                    //matchAlreadyInCourseEntry = ""
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


        } else {

            _courseEntries.value.add(
                FourGpData(
                    _fourSgpaUiState.value.courseCode.uppercase(Locale.UK),
                    _fourSgpaUiState.value.selectedCourseGrade,
                    _fourSgpaUiState.value.selectedCourseUnit.toInt()
                )
            )
            savedStateHandle.set(FOUR_SGPA_COURSE_ENTRIES_KEY, _courseEntries.value)


            _fourSgpaUiState.value.arrayOfAlreadyEnteredCourseslist.add(
                _fourSgpaUiState.value.courseCode.uppercase(
                    Locale.UK
                )
            )
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)



            _fourSgpaUiState.update {
                it.copy(
                    allReadyInList = false,
                    enteredCourses = _courseEntries.value.size.toString(),
                    courseEntryDialogBoxVisibility = false,
                    //matchAlreadyInCourseEntry =

                )
            }

            if (_fourSgpaUiState.value.totalCourses == _fourSgpaUiState.value.enteredCourses) {
                _fourSgpaUiState.update {
                    it.copy(
                        changeDoneIcon = true
                    )
                }


            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            clearCourseDataEntry()


        }


    }


    private fun textFieldsErrorCheckSaveFourCgpaResultAsDataEntry() {
        if (
            _fourCgpaUiState.value.saveResultAs.isEmpty()
        ) {
            _fourCgpaUiState.update {
                it.copy(
                    defaultLabelSRA = FourErrorMessages.errorMessageForCgpaSRA,
                    defaultLabelColourSRA = FourErrorMessages.textFieldErrorLabelColorHexCode,
                    saveResultDBVisibilty = true
                )
            }
            savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
        } else if (_fourCgpaUiState.value.saveResultAs.isNotEmpty()) {
            viewModelScope.launch {
                myFourCgpaRepository.InsertFourCgpaResult(
                    FourCgpaResultEntity(
                        resultName = _fourCgpaUiState.value.saveResultAs.uppercase(),
                        gp = _fourCgpaUiState.value.cgpa,
                        remark = _fourCgpaUiState.value.remark,
                        resultEntries = _fourCgpaUiState.value.sgpaResultNames,
                        resultGpaDescriptor = _fourCgpaUiState.value.gpaDescriptor

                    )
                )
            }
            _fourCgpaUiState.update {
                it.copy(
                    defaultLabelColourSRA = FourErrorPassedValues.errorPassedColour,
                    defaultLabelSRA = FourErrorPassedValues.labelForSRA,
                    saveResultDBVisibilty = false,
                    saveResultAs = ""
                )
            }
            savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            Log.d("names", "${_fourCgpaUiState.value.sgpaResultNames}")


        }


        // savedStateHandle.set(FourGpaViewModel.FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


    }

    private fun resetFourCgpaSRADBox() {
        _fourCgpaUiState.update {
            it.copy(
                defaultLabelSRA = FourErrorPassedValues.labelForSRA,
                defaultLabelColourSRA = FourErrorPassedValues.errorPassedColour,
                // saveResultDBVisibilty = false,
                //saveResultAs = ""


            )
        }
        savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
    }


    private fun textFieldsErrorCheckAndDuplicateEntrySaveFourSgpaResultAsDataEntry(
        resultNameForCheck: String
    ) {
        if (_fourSgpaUiState.value.saveResultAs.isEmpty()) {
            _fourSgpaUiState.update {
                it.copy(
                    defaultLabelSRA = FourErrorMessages.errorMessageForSRA,
                    defaultLabelColourSRA = FourErrorMessages.textFieldErrorLabelColorHexCode,
                    saveResultAsDialogBoxVisibility = true
                )
            }

        } else if (_fourSgpaUiState.value.saveResultAs.isNotEmpty()) {
//            for (i in 0 until _fourSgparesultIntroDB.value.resultItems.size) {
//                if (_fourSgparesultIntroDB.value.resultItems[i].resultName.uppercase() == resultNameForCheck.uppercase()) {
//                    Log.d(
//                        "duplicate name",
//                        "the  name ${_fourSgparesultIntroDB.value.resultItems[i].resultName} is repeating"
//                    )
//                    _fourSgpaUiState.update {
//                        it.copy(
//                            defaultLabelSRA = FourErrorMessages.errorDuplicateNameForSRA,
//                            defaultLabelColourSRA = FourErrorMessages.textFieldErrorLabelColorHexCode,
//                            saveResultAsDialogBoxVisibility = true
//                        )
//                    }                 //
//
//                }
//
//
//            }


//            _fourSgpaUiState.update {
//                it.copy(
//                    FourSgpaSRAToastNotifier = true
//                )
//
//            }

            //Toast.makeText(getActivity())
            viewModelScope.launch {
                val execTime = measureTimeMillis {
                    myFourSgpaRepository.InsertFourSgpaResult(
                        FourSgpaResultEntity(
                            resultEntries = _courseEntries.value,
                            gp = _fourSgpaUiState.value.fourSgpaFinalResult,
                            resultName = _fourSgpaUiState.value.saveResultAs.uppercase(),
                            remark = _fourSgpaUiState.value.remark,
                            resultGpaDescriptor = _fourSgpaUiState.value.gpaDescriptor

                        )
                    )
                    _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.cgpaList.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)
                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()
                    savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

                }
                Log.d("ViewModel", "Exec time: ${execTime}")
                delay(1000)
                _fourSgpaUiState.update {
                    it.copy(
                        fourSgpaSRAToastNotifier = true
                    )

                }

                // delay(100)

//                _fourSgpaUiState.update {
//                    it.copy(
//                        FourSgpaSRAToastNotifier = false
//                    )
//
//                }

            }
            _fourSgpaUiState.update {
                it.copy(
                    // FourSgpaSRAToastNotifier = false,
                    saveResultAsDialogBoxVisibility = false,
                    saveResultAs = ""
                )
            }

//            viewModelScope.launch {
//
//                delay(1000)
//                _fourSgpaUiState.update {
//                    it.copy(
//                        FourSgpaSRAToastNotifier = false,
//                        // saveResultAsDialogBoxVisibility = false,
//                    )
//                }
//
//            }
            resetFourSgpaSRADBox()


        }




        savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


    }


    private fun resetFourSgpaSRADBox() {
        _fourSgpaUiState.update {
            it.copy(
                defaultLabelSRA = FourErrorPassedValues.labelForSRA,
                defaultLabelColourSRA = FourErrorPassedValues.errorPassedColour,
                //saveResultAsDialogBoxVisibility = false,
                //saveResultAs = ""


            )
        }
        savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)
    }

    private fun operations(totalCreditLoad: Int): String {

        coursesDataEntryObj.sixUnitCoursesPointSum =
            (coursePointObj.sixUnitA.sum() + coursePointObj.sixUnitAB.sum() + coursePointObj.sixUnitB.sum() + coursePointObj.sixUnitBC.sum() + coursePointObj.sixUnitC.sum() + coursePointObj.sixUnitCD.sum() + coursePointObj.sixUnitD.sum() + coursePointObj.sixUnitE.sum() + coursePointObj.sixUnitF.sum())


        coursesDataEntryObj.fiveUnitCoursesPointSum =
            (coursePointObj.fiveUnitA.sum() + coursePointObj.fiveUnitAB.sum() + coursePointObj.fiveUnitB.sum() + coursePointObj.fiveUnitBC.sum() + coursePointObj.fiveUnitC.sum() + coursePointObj.fiveUnitCD.sum() + coursePointObj.fiveUnitD.sum() + coursePointObj.fiveUnitE.sum() + coursePointObj.fiveUnitF.sum())





        coursesDataEntryObj.fourUnitCoursesPointSum =
            (coursePointObj.fourUnitA.sum() + coursePointObj.fourUnitAB.sum() + coursePointObj.fourUnitB.sum() + coursePointObj.fourUnitBC.sum() + coursePointObj.fourUnitC.sum() + coursePointObj.fourUnitCD.sum() + coursePointObj.fourUnitD.sum() + coursePointObj.fourUnitE.sum() + coursePointObj.fourUnitF.sum())



        coursesDataEntryObj.threeUnitCoursesPointSum =
            (coursePointObj.threeUnitA.sum() + coursePointObj.threeUnitAB.sum() + coursePointObj.threeUnitB.sum() + coursePointObj.threeUnitBC.sum() + coursePointObj.threeUnitC.sum() + coursePointObj.threeUnitCD.sum() + coursePointObj.threeUnitD.sum() + coursePointObj.threeUnitE.sum() + coursePointObj.threeUnitF.sum())



        coursesDataEntryObj.twoUnitCoursesPointSum =
            (coursePointObj.twoUnitA.sum() + coursePointObj.twoUnitAB.sum() + coursePointObj.twoUnitB.sum() + coursePointObj.twoUnitBC.sum() + coursePointObj.twoUnitC.sum() + coursePointObj.twoUnitCD.sum() + coursePointObj.twoUnitD.sum() + coursePointObj.twoUnitE.sum() + coursePointObj.twoUnitF.sum())


        coursesDataEntryObj.oneUnitCoursesPointSum =
            (coursePointObj.oneUnitA.sum() + coursePointObj.oneUnitAB.sum() + coursePointObj.oneUnitB.sum() + coursePointObj.oneUnitBC.sum() + coursePointObj.oneUnitC.sum() + coursePointObj.oneUnitCD.sum() + coursePointObj.oneUnitD.sum() + coursePointObj.oneUnitE.sum() + coursePointObj.oneUnitF.sum())

        coursesDataEntryObj.totalCoursesPointSum =
            (coursesDataEntryObj.sixUnitCoursesPointSum + coursesDataEntryObj.fiveUnitCoursesPointSum + coursesDataEntryObj.fourUnitCoursesPointSum + coursesDataEntryObj.threeUnitCoursesPointSum + coursesDataEntryObj.twoUnitCoursesPointSum + coursesDataEntryObj.oneUnitCoursesPointSum)


        var finalAns = (coursesDataEntryObj.totalCoursesPointSum / totalCreditLoad)
        //var decimalFormat = DecimalFormat("#.##")
        var final_result = String.format("%.2f", finalAns)

        Log.d(
            "Ahh!!!",
            "${coursePointObj.oneUnitD.sum()}...${coursesDataEntryObj.oneUnitCoursesPointSum}...${coursesDataEntryObj.totalCoursesPointSum}...${totalCreditLoad}...${finalAns}"
        )


        return ("$final_result")
    }

    private fun courseValueMapper(courseGrade: ArrayList<FourGpData>) {


        courseGrade.forEach { courseData ->

            when (courseData) {

                //For six unit Courses

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["A"]?.let { coursePointObj.sixUnitA.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "AB",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["AB"]?.let { coursePointObj.sixUnitAB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["B"]?.let { coursePointObj.sixUnitB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "BC",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["BC"]?.let { coursePointObj.sixUnitBC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["C"]?.let { coursePointObj.sixUnitC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "CD",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["CD"]?.let { coursePointObj.sixUnitCD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["D"]?.let { coursePointObj.sixUnitD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["E"]?.let { coursePointObj.sixUnitE.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 6
                ) -> {

                    courseMapObj.sixUnit_GradeMap["F"]?.let { coursePointObj.sixUnitF.add(it) }

                }

                //For five unit courses
                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["A"]?.let { coursePointObj.fiveUnitA.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "AB",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["AB"]?.let { coursePointObj.fiveUnitA.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["B"]?.let { coursePointObj.fiveUnitB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "BC",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["BC"]?.let { coursePointObj.fiveUnitB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["C"]?.let { coursePointObj.fiveUnitC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "CD",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["CD"]?.let { coursePointObj.fiveUnitC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["D"]?.let { coursePointObj.fiveUnitD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["E"]?.let { coursePointObj.fiveUnitE.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 5
                ) -> {

                    courseMapObj.fiveUnit_GradeMap["F"]?.let { coursePointObj.fiveUnitF.add(it) }

                }


                //For four unit Courses

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["A"]?.let { coursePointObj.fourUnitA.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "AB",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["AB"]?.let { coursePointObj.fourUnitAB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["B"]?.let { coursePointObj.fourUnitB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "BC",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["BC"]?.let { coursePointObj.fourUnitBC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["C"]?.let { coursePointObj.fourUnitC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "CD",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["CD"]?.let { coursePointObj.fourUnitCD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["D"]?.let { coursePointObj.fourUnitD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["E"]?.let { coursePointObj.fourUnitE.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 4
                ) -> {

                    courseMapObj.fourUnit_GradeMap["F"]?.let { coursePointObj.fourUnitF.add(it) }

                }


                //For three unit Courses

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["A"]?.let { coursePointObj.threeUnitA.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "AB",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["BA"]?.let { coursePointObj.threeUnitAB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["B"]?.let { coursePointObj.threeUnitB.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "BC",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["BC"]?.let { coursePointObj.threeUnitBC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["C"]?.let { coursePointObj.threeUnitC.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "CD",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["CD"]?.let { coursePointObj.threeUnitCD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["D"]?.let { coursePointObj.threeUnitD.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["E"]?.let { coursePointObj.threeUnitE.add(it) }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "F",
                    courseUnit = 3
                ) -> {

                    courseMapObj.threeUnit_GradeMap["F"]?.let { coursePointObj.threeUnitF.add(it) }

                }


                //For two unit courses:

                FourGpData(courseCode = courseData.courseCode, "A", 2) -> {

                    courseMapObj.twoUnit_GradeMap["A"]?.let {

                        coursePointObj.twoUnitA.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "AB", 2) -> {

                    courseMapObj.twoUnit_GradeMap["AB"]?.let {

                        coursePointObj.twoUnitAB.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "B", 2) -> {

                    courseMapObj.twoUnit_GradeMap["B"]?.let {

                        coursePointObj.twoUnitB.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "BC", 2) -> {

                    courseMapObj.twoUnit_GradeMap["BC"]?.let {

                        coursePointObj.twoUnitBC.add(it)
                    }

                }


                FourGpData(courseCode = courseData.courseCode, "C", 2) -> {

                    courseMapObj.twoUnit_GradeMap["C"]?.let {

                        coursePointObj.twoUnitC.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "CD", 2) -> {

                    courseMapObj.twoUnit_GradeMap["CD"]?.let {

                        coursePointObj.twoUnitCD.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "D", 2) -> {

                    courseMapObj.twoUnit_GradeMap["D"]?.let {

                        coursePointObj.twoUnitD.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "E", 2) -> {

                    courseMapObj.twoUnit_GradeMap["E"]?.let {

                        coursePointObj.twoUnitE.add(it)
                    }

                }

                FourGpData(courseCode = courseData.courseCode, "A", 2) -> {

                    courseMapObj.twoUnit_GradeMap["F"]?.let {

                        coursePointObj.twoUnitF.add(it)
                    }

                }

                //For one unit Courses

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "A",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["A"]?.let {
                        coursePointObj.oneUnitA.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "AB",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["AB"]?.let {
                        coursePointObj.oneUnitAB.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "B",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["B"]?.let {
                        coursePointObj.oneUnitB.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "BC",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["BC"]?.let {
                        coursePointObj.oneUnitBC.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "C",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["C"]?.let {
                        coursePointObj.oneUnitC.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "CD",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["CD"]?.let {
                        coursePointObj.oneUnitCD.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "D",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["D"]?.let {
                        coursePointObj.oneUnitD.add(it)
                    }

                }

                FourGpData(
                    courseCode = courseData.courseCode,
                    courseGrade = "E",
                    courseUnit = 1
                ) -> {

                    courseMapObj.oneUnit_GradeMap["E"]?.let {
                        coursePointObj.oneUnitE.add(it)
                    }

                }

                FourGpData(
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
        _fourSgpaUiState.update {
            it.copy(
                courseCode = "",
                selectedCourseUnit = "",
                selectedCourseGrade = "",
                pickedCourseGradeDefaultLabel = FourErrorPassedValues.enterCourseGradeLabel,
                pickedCourseUnitDefaultLabel = FourErrorPassedValues.enterCourseUnitLabel,
                //defaultEnteredCourseCodeLabel = FourErrorPassedValues.enterCourseCodeLabel,

            )

        }
        savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

    }

    private fun onReExecuteCalculationClearArrayField() {

        coursePointObj.sixUnitA.clear()
        coursePointObj.sixUnitAB.clear()
        coursePointObj.sixUnitB.clear()
        coursePointObj.sixUnitBC.clear()
        coursePointObj.sixUnitC.clear()
        coursePointObj.sixUnitCD.clear()
        coursePointObj.sixUnitD.clear()
        coursePointObj.sixUnitE.clear()
        coursePointObj.sixUnitF.clear()

        coursePointObj.fiveUnitA.clear()
        coursePointObj.fiveUnitAB.clear()
        coursePointObj.fiveUnitB.clear()
        coursePointObj.fiveUnitBC.clear()
        coursePointObj.fiveUnitC.clear()
        coursePointObj.fiveUnitCD.clear()
        coursePointObj.fiveUnitD.clear()
        coursePointObj.fiveUnitE.clear()
        coursePointObj.fiveUnitF.clear()

        coursePointObj.fourUnitA.clear()
        coursePointObj.fourUnitAB.clear()
        coursePointObj.fourUnitB.clear()
        coursePointObj.fourUnitBC.clear()
        coursePointObj.fourUnitC.clear()
        coursePointObj.fourUnitCD.clear()
        coursePointObj.fourUnitD.clear()
        coursePointObj.fourUnitE.clear()
        coursePointObj.fourUnitF.clear()

        coursePointObj.threeUnitA.clear()
        coursePointObj.threeUnitAB.clear()
        coursePointObj.threeUnitB.clear()
        coursePointObj.threeUnitBC.clear()
        coursePointObj.threeUnitC.clear()
        coursePointObj.threeUnitCD.clear()
        coursePointObj.threeUnitD.clear()
        coursePointObj.threeUnitE.clear()
        coursePointObj.threeUnitF.clear()


        coursePointObj.twoUnitA.clear()
        coursePointObj.twoUnitAB.clear()
        coursePointObj.twoUnitB.clear()
        coursePointObj.twoUnitBC.clear()
        coursePointObj.twoUnitC.clear()
        coursePointObj.twoUnitCD.clear()
        coursePointObj.twoUnitD.clear()
        coursePointObj.twoUnitE.clear()
        coursePointObj.twoUnitF.clear()

        coursePointObj.oneUnitA.clear()
        coursePointObj.oneUnitAB.clear()
        coursePointObj.oneUnitB.clear()
        coursePointObj.oneUnitBC.clear()
        coursePointObj.oneUnitC.clear()
        coursePointObj.oneUnitCD.clear()
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
            _fourSgpaUiState.update {
                it.copy(
                    greeting = "Good Morning"
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            println("Good Morning")
        } else if (afternoon.contains(myHour)) {
            _fourSgpaUiState.update {
                it.copy(
                    greeting = "Good Afternoon"
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            println("Good Afternoon")

        } else if (evening.contains(myHour)) {

            _fourSgpaUiState.update {
                it.copy(
                    greeting = "Good Evening"
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            println("Good Evening")

        } else if (night.contains(myHour)) {

            _fourSgpaUiState.update {
                it.copy(
                    greeting = "Good Evening"
                )
            }
            savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            println("Good Night")

        }
    }

    private fun GpaDescriptor(gpa: Float, desc: String) {
        if (desc == "sgpa") {
            if (gpa in 3.50..4.00) {
                _fourSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Distinction",
                        remark = "You Performed Brilliantly"
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)

            } else if (gpa in 3.00..3.49) {

                _fourSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Upper Credit",
                        remark = "You Performed Amazing "
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            } else if (gpa in 2.50..2.99) {

                _fourSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Lower Credit",
                        remark = "You Performed Great"
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            } else if (gpa in 2.00..2.49) {

                _fourSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Pass",
                        remark = "You performed averagely"
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            } else if (gpa in 0.00..1.99) {

                _fourSgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Fail",
                        remark = "You failed"
                    )
                }
                savedStateHandle.set(FOUR_SGPA_UI_STATE_KEY, _fourSgpaUiState.value)


            }

        } else if (desc == "cgpa") {

            if (gpa in 3.50..4.00) {
                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Distinction",
                        remark = "You Performed Brilliantly"
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)

            } else if (gpa in 3.00..3.49) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Upper Credit",
                        remark = "You Performed Amazing "
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            } else if (gpa in 2.50..2.99) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Lower Credit",
                        remark = "You Performed Great"
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            } else if (gpa in 2.00..2.49) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Pass",
                        remark = "You performed averagely"
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            } else if (gpa in 0.00..1.99) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Fail",
                        remark = "You failed"
                    )
                }
                savedStateHandle.set(FOUR_CGPA_UI_STATE_KEY, _fourCgpaUiState.value)


            }


        }


    }

}
