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
        private const val DB_STATE_KEY = "my_db_state"
        private const val COURSE_ENTRIES_KEY = "my_course_entry_state"
    }

    private val coursePointObj = FourCoursesUnitPointArrayList()
    private val courseMapObj = FourCourseMaps()
    private val coursesDataEntryObj = FourCourseDataEntries()
    private val stateClassObject = FourSgpaUiStates()
    private var result = ""
    var coursesUnitSubList = ArrayList<Int>()


    private var _courseEntries = MutableStateFlow(
        savedStateHandle.get(COURSE_ENTRIES_KEY) ?: FourCourseDataEntries().coursesDataEntry
    )
    var courseEntries = _courseEntries.asStateFlow()

    private var _dbState =
        MutableStateFlow(savedStateHandle.get(DB_STATE_KEY) ?: FourSgpaUiStates())
    var dbState = _dbState.asStateFlow()


    private var _fourSgparesultIntroDB = MutableStateFlow(FourSgpaResultsRecordState())
    val fourSgparesultIntroDB = _fourSgparesultIntroDB.asStateFlow()

    private var _fourCgpaResultIntroDB = MutableStateFlow(FourCgpaResultsRecordState())
    val fourCgpaResultIntroDB = _fourCgpaResultIntroDB.asStateFlow()

    private var _fourCgpaUiState =
        MutableStateFlow(FourCgpaUiStates())
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
                    //  _resultIntroDB.value.resultItems = result

                }


        }

    }

    private fun loadFourSgpaData(chkBoxState: Boolean = false, pseudoIndex: Int = 0) {
        viewModelScope.launch {
            _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()

            myFourSgpaRepository.GetFourSgpaResultRecordDao()
                .collect { result ->

                    _fourSgparesultIntroDB.update {
                        it.copy(
                            resultItems = result
                        )
                    }

                    for (i in 0 until result.size) {

                        _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.add(
                            SgpaResultDisplayFormatForFourCgpaCalculation(
                                resultSelected = false,
                                resultName = result.get(i).resultName,
                                resultSgpa = result.get(i).gp
                            )
                        )


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
            }

            is FourGpaUiEvents.helpFourCgpa -> {

                _fourCgpaUiState.update {
                    it.copy(
                        newHelperText = "Ahh..."
                    )
                }
            }

            is FourGpaUiEvents.setFourCgpaSRA -> {
                _fourCgpaUiState.update {
                    it.copy(
                        saveResultAs = event.saveResultAs
                    )
                }

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
                    }

                    _fourCgpaUiState.update {
                        it.copy(
                            cgpa = String.format(
                                "%.2f",
                                _fourCgpaUiState.value.cgpaList.sum() / _fourCgpaUiState.value.cgpaList.size
                            )
                        )
                    }

                    GpaDescriptor(_fourCgpaUiState.value.cgpa.toFloat(), "cgpa")

                }

                Log.d(
                    "CGPA",
                    "Your sum is ${_fourCgpaUiState.value.cgpaList.sum()} and size is ${_fourCgpaUiState.value.cgpaList.size}"
                )
                Log.d("CGPA", "Your cgpa is ${_fourCgpaUiState.value.cgpa}")

                //_fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                _fourCgpaUiState.value.cgpaList.clear()
                //_fourCgpaUiState.value.sgpaListToBeCalculated.clear()
                //loadData()
            }

            is FourGpaUiEvents.onCheckChanged -> {


                _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.get(event.index).resultSelected =
                    event.isChecked

                for (i in 0.._fourCgpaUiState.value.displayedResultForFourCgpaCalculation.size - 1) {
                    if (_fourCgpaUiState.value.displayedResultForFourCgpaCalculation[i].resultSelected) {
                        _fourCgpaUiState.update {
                            it.copy(operatorIconState = true)

                        }
                        Log.d(
                            "StatusIconCheck",
                            "Your status are ${_fourCgpaUiState.value.displayedResultForFourCgpaCalculation}"
                        )

                    } else if (_fourCgpaUiState.value.displayedResultForFourCgpaCalculation[i].resultSelected == false && event.isChecked == false) {
                        _fourCgpaUiState.update {
                            it.copy(operatorIconState = false)
                        }
                    }

                    "Your status are ${_fourCgpaUiState.value.displayedResultForFourCgpaCalculation}"


                }

                val randomNumber = Random.nextInt(1, 1000)
                _fourCgpaUiState.update {
                    it.copy(
                        helperText = randomNumber.toString()
                    )
                }


                if (event.isChecked == true) {
                    _fourCgpaUiState.update {
                        it.copy(operatorIconState = true)
                    }

                    _fourCgpaUiState.value.sgpaListToBeCalculated.add(
                        //index = event.index,
                        ResultTracker(
                            id = event.index,
                            sgpaResult = event.sgpaNeeded,
                            resultName = event.resultNameRef
                        )
                    )
                    _fourCgpaUiState.value.sgpaResultNames.add(event.resultNameRef)
                } else {
                    _fourCgpaUiState.value.sgpaListToBeCalculated.removeIf {
                        it.id == event.index
                    }
                    _fourCgpaUiState.value.sgpaResultNames.remove(event.resultNameRef)

                }

                // println(_fourCgpaUiState.value.sgpaListToBeCalculated)
                Log.d("List of sgpa", "${_fourCgpaUiState.value.sgpaListToBeCalculated}")
            }

            is FourGpaUiEvents.DeleteFourGpaResultByReference -> {
                _fourCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
                viewModelScope.launch {
                    myFourSgpaRepository.FourSgpaResultToBeDeleted(event.fourSgpaResultName)
                    _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                    _fourCgpaUiState.value.cgpaList.clear()
                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()

                }
            }

            is FourGpaUiEvents.DeleteFourCgpaResultByReference -> {
                _fourCgpaUiState.update {
                    it.copy(operatorIconState = false)
                }
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
                    _fourCgpaUiState.value.cgpaList.clear()
                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()


                }

            }

            is FourGpaUiEvents.showResultDBox -> {
                _dbState.update {
                    it.copy(
                        resultDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideFourSgpaSaveResultDB -> {
                _dbState.update {
                    it.copy(
                        saveResultAsDialogBoxVisibility = false,
                        saveResultAs = ""

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.setSRA -> {
                _dbState.update {
                    it.copy(
                        saveResultAs = event.savedResultName

                    )
                }

            }

            is FourGpaUiEvents.saveFourSgpaResult -> {

//                _dbState.update {
//                    it.copy(
//                        FourSgpaSRAToastNotifier = true
//                    )
//                }
                textFieldsErrorCheckAndDuplicateEntrySaveFourSgpaResultAsDataEntry(_dbState.value.saveResultAs)


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

                _dbState.update {
                    it.copy(
                        courseCode = event.courseCodeEdit,
                        selectedCourseUnit = event.unitEdit,
                        selectedCourseGrade = event.gradeEdit

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.updateCourseIndexEntry -> {
                _dbState.update {
                    it.copy(
                        courseEntryIndex = event.entryIndex
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showCourseEntryEditDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = true,
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideCourseEntryEditDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }


            is FourGpaUiEvents.resetResultField -> {

                _dbState.update {
                    it.copy(
                        fourSgpaFinalResult = "new val"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showDataEntryDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is FourGpaUiEvents.hideDataEntryDBox -> {
                _dbState.update {
                    it.copy(
                        allReadyInList = false,
                        courseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showUnitMenuDropDown -> {

                _dbState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is FourGpaUiEvents.hideUnitMenuDropDown -> {

                _dbState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is FourGpaUiEvents.showGradeMenuDropDown -> {
                _dbState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideGradeMenuDropDown -> {
                _dbState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.setSelectedCourseGrade -> {

                _dbState.update {
                    it.copy(
                        selectedCourseGrade = event.grade
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is FourGpaUiEvents.setSelectedCourseUnit -> {
                _dbState.update {
                    it.copy(
                        selectedCourseUnit = event.unit
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.deleteCourseEntry -> {

                _courseEntries.value.removeAt(event.itemToRemove)
                savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)
                _dbState.value.arrayOfAlreadyEnteredCourseslist.removeAt(event.itemToRemove)
                _dbState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString()
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.setCourseCode -> {

                if (_dbState.value.arrayOfAlreadyEnteredCourseslist.contains(event.courseCode.uppercase())) {
                    _dbState.update {
                        it.copy(
                            allReadyInList = true,
                            matchAlreadyInCourseEntry = event.courseCode.uppercase()
                        )
                    }
                } else {
                    _dbState.update {
                        it.copy(
                            allReadyInList = false,
                            matchAlreadyInCourseEntry = ""

                        )
                    }

                }
                _dbState.update {
                    it.copy(
                        courseCode = event.courseCode.replace(" ", "")
                    )


                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

//                } else {
//                    _dbState.update {
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
                _dbState.update {
                    it.copy(
                        totalCreditLoad = event.totalCreditLoad
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.setTotalCourses -> {
//                var use = event.totalCourses
//                if (use[0] == '0') {
//                    use = use.replace("0", "")
//                    _dbState.update {
//                        it.copy(
//                            totalCourses = use
//                        )
//                    }
//                    savedStateHandle.set(DB_STATE_KEY, _dbState.value)
//
//
//                } else if (use[0] == '0' && use[1] == '0') {
//                    use = use.replace("0", "")
//                    _dbState.update {
//                        it.copy(
//                            totalCourses = use
//                        )
//                    }
//                    savedStateHandle.set(DB_STATE_KEY, _dbState.value)
//
//
//                } else {
                _dbState.update {
                    it.copy(
                        totalCourses = event.totalCourses
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


                // }


            }

            is FourGpaUiEvents.hideBaseEntryDBox -> {
                textFieldsErrorCheckBaseEntryDB()

            }

            is FourGpaUiEvents.showSaveResultDBox -> {
                _dbState.update {
                    it.copy(
                        saveResultAsDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

//            is FourGpaUiEvents.hideFourSgpaSaveResultDB -> {
//                _dbState.update {
//
//                    it.copy(
//                        saveResultAsDialogBoxVisibility = false,
//                    )
//                }
//                savedStateHandle.set(DB_STATE_KEY, _dbState.value)
//
//            }

            is FourGpaUiEvents.resetAlreadyInList -> {
                _dbState.update {
                    it.copy(
                        allReadyInList = false
                    )

                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is FourGpaUiEvents.showCourseDataEntriesContextmenu -> {

                _dbState.update {
                    it.copy(
                        courseItemsDropDownVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is FourGpaUiEvents.hideCourseDataEntriesContextmenu -> {
                _dbState.update {
                    it.copy(
                        courseItemsDropDownVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.resetTotalEntries -> {


                if (_dbState.value.totalCourses > 0.toString()) {


                    _dbState.value.arrayOfAlreadyEnteredCourseslist.clear()
                    _courseEntries.value.clear()
                    _dbState.update {
                        it.copy(
                            //totalCourses = "",
                            totalCreditLoad = "",
                            enteredCourses = "0",

                            //baseEntryDialogBoxVisibility = true
                        )
                    }
                    savedStateHandle.set(DB_STATE_KEY, _dbState.value)


                } else {


                }


            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC -> {
                _dbState.update {
                    it.copy(
                        defaultLabelColourTNOC = FourErrorPassedValues.errorPassedColour,
                        defaultLabelTNOC = FourErrorPassedValues.labelForTNOC

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCC -> {
                _dbState.update {
                    it.copy(
                        defaultLabelColourCC = FourErrorPassedValues.errorPassedColour,
                        defaultEnteredCourseCodeLabel = FourErrorPassedValues.enterCourseCodeLabel

                    )
                }

            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsECC -> {
                _dbState.update {
                    it.copy(
                        defaultLabelColourECC = FourErrorPassedValues.errorPassedColour,
                        defaultEditCourseCodeLabel = FourErrorPassedValues.editCourseCodeLabel

                    )
                }

            }


            is FourGpaUiEvents.resetDefaultValuesFromErrorsTNOCL -> {
                _dbState.update {
                    it.copy(
                        defaultLabelTNOCL = FourErrorPassedValues.labelForTNOCC,
                        //defaultLabelColourTNOCL = FourErrorPassedValues.errorPassedColour

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showEditBaseEntryDBox -> {
                _dbState.update {
                    it.copy(
                        editBaseEntryDialogBoxVisibility = true,
                        errorToastMessageVisibilityETNOCDB = true

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideEditBaseEntryDBox -> {

                textFieldsErrorEditedCheckBaseEntryDB()

                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideEditBaseEntryRegardlessDBox -> {

                if (_dbState.value.enteredCourses == "0") {

                    _dbState.update {
                        it.copy(
                            editBaseEntryDialogBoxVisibility = false,
                            totalCourses = _dbState.value.prevTotalNumberOfCourses
                        )
                    }

                    savedStateHandle.set(DB_STATE_KEY, _dbState.value)

                } else if (_dbState.value.enteredCourses != "0") {

                    _dbState.update {
                        it.copy(
                            editBaseEntryDialogBoxVisibility = false,
                            totalCourses = _dbState.value.enteredCourses

                        )
                    }

                    savedStateHandle.set(DB_STATE_KEY, _dbState.value)


                }


            }


            is FourGpaUiEvents.showBaseEntryDBox -> {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    greetings()
                }

                _dbState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }


            is FourGpaUiEvents.executeCalculation -> {

                _dbState.update {
                    it.copy(
                        changeDoneIcon = true
                    )
                }

                var execTotalUnit: Int = 0


                for (i in 1.._dbState.value.totalCourses.toInt()) {
                    coursesUnitSubList.add(_courseEntries.value[i - 1].courseUnit)
                    savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)

                }
                execTotalUnit = coursesUnitSubList.sum()
                _dbState.update {
                    it.copy(
                        totalCreditLoad = execTotalUnit.toString()
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)



                courseValueMapper(_courseEntries.value)
                result = operations(_dbState.value.totalCreditLoad.toInt())
                _dbState.update {
                    it.copy(
                        fourSgpaFinalResult = result
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


                GpaDescriptor(_dbState.value.fourSgpaFinalResult.toFloat(), "sgpa")

                onReExecuteCalculationClearArrayField()


            }

            is FourGpaUiEvents.hideBaseEntryRegardlessDBox -> {
                _dbState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = false,
                        totalCourses = "",
                        prevTotalNumberOfCourses = ""

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.setPrevTotalCourses -> {
                _dbState.update {
                    it.copy(
                        prevTotalNumberOfCourses = event.text
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showClearConfirmationDBox -> {
                _dbState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideClearConfirmationDBox -> {
                _dbState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showHomeAdShimmerEffect -> {
                _dbState.update {
                    it.copy(
                        homeAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideHomeAdShimmerEffect -> {
                _dbState.update {
                    it.copy(
                        homeAdShimmerEffectVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.showAboutAdShimmerEffect -> {
                _dbState.update {
                    it.copy(
                        aboutAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.hideAboutAdShimmerEffect -> {
                _dbState.update {
                    it.copy(
                        aboutAdShimmerEffectVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is FourGpaUiEvents.setTotalNumberOfEditedCourses -> {
                _dbState.update {
                    it.copy(
                        editedNumberOfCourses = event.noOfEditedTotalCourse
                    )
                }
            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCU -> {
                _dbState.update {
                    it.copy(
                        pickedCourseUnitDefaultLabel = FourErrorPassedValues.enterCourseUnitLabel,
                        defaultLabelColourCU = FourErrorPassedValues.dropDownErrorPassedColour
                    )
                }
            }

            is FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCG -> {
                _dbState.update {
                    it.copy(
                        pickedCourseGradeDefaultLabel = FourErrorPassedValues.enterCourseGradeLabel,
                        defaultLabelColourCG = FourErrorPassedValues.dropDownErrorPassedColour
                    )
                }
            }


            else -> {}
        }

    }


    private fun textFieldsErrorCheckBaseEntryDB() {

        if (_dbState.value.totalCourses.isEmpty() || _dbState.value.totalCourses == "0" || _dbState.value.totalCourses == "00" || _dbState.value.totalCourses.get(
                0
            ) == '0'
        ) {

            _dbState.update {
                it.copy(
                    defaultLabelTNOC = FourErrorMessages.errorLabelMessageForTNOC,
                    defaultLabelColourTNOC = FourErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.totalCreditLoad.isNotEmpty()) {
            _dbState.update {
                it.copy(
                    baseEntryDialogBoxVisibility = false
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        }


    }

    private fun textFieldsErrorEditedCheckBaseEntryDB() {

        if (_dbState.value.totalCourses == _dbState.value.prevTotalNumberOfCourses && _dbState.value.enteredCourses == "0") {
            _dbState.update {
                it.copy(
                    errorMessageHolderForETNOCDBToastMessage = "No changes made",
                    editBaseEntryDialogBoxVisibility = false,
                    errorToastMessageVisibilityETNOCDB = false,

                    )
            }
        } else if (_dbState.value.editedNumberOfCourses.isEmpty() || _dbState.value.editedNumberOfCourses == "0" || _dbState.value.editedNumberOfCourses == "00" || _dbState.value.editedNumberOfCourses.get(
                0
            ) == '0'
        ) {
            if (_dbState.value.enteredCourses == "0") {
                _dbState.update {
                    it.copy(
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be empty",
                        totalCourses = _dbState.value.prevTotalNumberOfCourses,
                        editedNumberOfCourses = _dbState.value.prevTotalNumberOfCourses,
                        errorToastMessageVisibilityETNOCDB = true,


                        )
                }
            } else if (_dbState.value.enteredCourses != "0") {
                _dbState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be empty",
                        totalCourses = _dbState.value.enteredCourses,
                        editedNumberOfCourses = _dbState.value.enteredCourses

                    )
                }
            }

            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.editedNumberOfCourses == "0") {

            if (_dbState.value.enteredCourses == "0") {
                _dbState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be 0",
                        totalCourses = _dbState.value.prevTotalNumberOfCourses,
                        editedNumberOfCourses = _dbState.value.prevTotalNumberOfCourses

                    )
                }
            } else if (_dbState.value.enteredCourses != "0") {
                _dbState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be 0",
                        totalCourses = _dbState.value.enteredCourses,
                        editedNumberOfCourses = _dbState.value.enteredCourses

                    )
                }
            }

            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.editedNumberOfCourses.isNotEmpty() && _dbState.value.enteredCourses != "0") {

            if (_dbState.value.editedNumberOfCourses.toInt() < dbState.value.enteredCourses.toInt()) {
                _dbState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = true,
                        errorMessageHolderForETNOCDBToastMessage = "entry can't be less than already entered courses",
                        totalCourses = _dbState.value.enteredCourses,
                        editedNumberOfCourses = _dbState.value.enteredCourses

                    )
                }
            } else if (_dbState.value.editedNumberOfCourses >= _dbState.value.enteredCourses) {
                _dbState.update {
                    it.copy(
                        errorToastMessageVisibilityETNOCDB = false,
                        errorMessageHolderForETNOCDBToastMessage = "successfully updated",
                        totalCourses = _dbState.value.editedNumberOfCourses,
                        editBaseEntryDialogBoxVisibility = false,
                        editedNumberOfCourses = _dbState.value.editedNumberOfCourses


                    )
                }
            }

            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else {
            _dbState.update {
                it.copy(
                    errorToastMessageVisibilityETNOCDB = false,
                    errorMessageHolderForETNOCDBToastMessage = "",
                    editBaseEntryDialogBoxVisibility = false,
                )
            }
        }


    }


    private fun textFieldsErrorCheckEditedCourseDataEntry() {

        if (_dbState.value.courseCode.isEmpty()) {

            _dbState.update {
                it.copy(
                    defaultEditCourseCodeLabel = FourErrorMessages.errorMessageForCourseCode,
                    defaultLabelColourECC = FourErrorMessages.textFieldErrorLabelColorHexCode


                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else {

            if (
                _courseEntries.value.contains(
                    FourGpData(
                        courseCode = _dbState.value.courseCode.uppercase(),
                        courseGrade = _dbState.value.selectedCourseGrade,
                        courseUnit = _dbState.value.selectedCourseUnit.toInt()
                    )
                )
            ) {

                _dbState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString(),
                        courseEntryEditDialogBoxVisibility = false,

                        )
                }


            } else {
                _courseEntries.value[_dbState.value.courseEntryIndex.toInt()] = FourGpData(
                    courseCode = _dbState.value.courseCode.uppercase(),
                    courseGrade = _dbState.value.selectedCourseGrade,
                    courseUnit = _dbState.value.selectedCourseUnit.toInt()
                )
                savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)


                _dbState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString(),
                        courseEntryEditDialogBoxVisibility = false
//                    courseEntryDialogBoxVisibility = false,

                    )
                }
                clearCourseDataEntry()
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }


        }


    }


    private fun textFieldsErrorCheckCourseDataEntry() {

        if (_dbState.value.courseCode.isEmpty()) {

            _dbState.update {
                it.copy(
                    defaultEnteredCourseCodeLabel = FourErrorMessages.errorMessageForCourseCode,
                    defaultLabelColourCC = FourErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.selectedCourseUnit.isEmpty()) {
            _dbState.update {
                it.copy(
                    pickedCourseUnitDefaultLabel = FourErrorMessages.errorMessageForCourseUnit,
                    defaultLabelColourCU = FourErrorMessages.textFieldErrorLabelColorHexCode

                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.selectedCourseGrade.isEmpty()) {

            _dbState.update {
                it.copy(
                    pickedCourseGradeDefaultLabel = FourErrorMessages.errorMessageForCourseGrade,
                    defaultLabelColourCG = FourErrorMessages.textFieldErrorLabelColorHexCode
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (


            _dbState.value.arrayOfAlreadyEnteredCourseslist.contains(
                _dbState.value.courseCode.uppercase(Locale.UK)
            )
        ) {


            _dbState.update {
                it.copy(
                    matchAlreadyInCourseEntry = _dbState.value.courseCode.uppercase(Locale.UK),
                    allReadyInList = true,

                    )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            savedStateHandle.set(DB_STATE_KEY, _dbState.value)
            _dbState.update {
                it.copy(
                    // allReadyInList = false,
                    //matchAlreadyInCourseEntry = ""
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else {

            _courseEntries.value.add(
                FourGpData(
                    _dbState.value.courseCode.uppercase(Locale.UK),
                    _dbState.value.selectedCourseGrade,
                    _dbState.value.selectedCourseUnit.toInt()
                )
            )
            savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)


            _dbState.value.arrayOfAlreadyEnteredCourseslist.add(
                _dbState.value.courseCode.uppercase(
                    Locale.UK
                )
            )
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)



            _dbState.update {
                it.copy(
                    allReadyInList = false,
                    enteredCourses = _courseEntries.value.size.toString(),
                    courseEntryDialogBoxVisibility = false,
                    //matchAlreadyInCourseEntry =

                )
            }

            if (_dbState.value.totalCourses == _dbState.value.enteredCourses) {
                _dbState.update {
                    it.copy(
                        changeDoneIcon = true
                    )
                }


            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

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
        } else if (_fourCgpaUiState.value.saveResultAs.isNotEmpty()) {
            viewModelScope.launch {
                myFourCgpaRepository.InsertFourCgpaResult(
                    FourCgpaResultEntity(
                        resultName = _fourCgpaUiState.value.saveResultAs.uppercase(),
                        gp = _fourCgpaUiState.value.cgpa,
                        remark = _fourCgpaUiState.value.remark,
                        resultEntries = _fourCgpaUiState.value.sgpaResultNames,

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

            Log.d("names", "${_fourCgpaUiState.value.sgpaResultNames}")


        }


        // savedStateHandle.set(FourGpaViewModel.DB_STATE_KEY, _dbState.value)


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
        //savedStateHandle.set(FourGpaViewModel.DB_STATE_KEY, _dbState.value)
    }


    private fun textFieldsErrorCheckAndDuplicateEntrySaveFourSgpaResultAsDataEntry(
        resultNameForCheck: String
    ) {
        if (_dbState.value.saveResultAs.isEmpty()) {
            _dbState.update {
                it.copy(
                    defaultLabelSRA = FourErrorMessages.errorMessageForSRA,
                    defaultLabelColourSRA = FourErrorMessages.textFieldErrorLabelColorHexCode,
                    saveResultAsDialogBoxVisibility = true
                )
            }

        } else if (_dbState.value.saveResultAs.isNotEmpty()) {
//            for (i in 0 until _fourSgparesultIntroDB.value.resultItems.size) {
//                if (_fourSgparesultIntroDB.value.resultItems[i].resultName.uppercase() == resultNameForCheck.uppercase()) {
//                    Log.d(
//                        "duplicate name",
//                        "the  name ${_fourSgparesultIntroDB.value.resultItems[i].resultName} is repeating"
//                    )
//                    _dbState.update {
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


//            _dbState.update {
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
                            gp = _dbState.value.fourSgpaFinalResult,
                            resultName = _dbState.value.saveResultAs.uppercase(),
                            remark = _dbState.value.remark,

                            )
                    )
                    _fourCgpaUiState.value.displayedResultForFourCgpaCalculation.clear()
                    _fourCgpaUiState.value.cgpaList.clear()
                    _fourCgpaUiState.value.sgpaListToBeCalculated.clear()
                }
                Log.d("ViewModel", "Exec time: ${execTime}")
                delay(1000)
                _dbState.update {
                    it.copy(
                        fourSgpaSRAToastNotifier = true
                    )

                }

                // delay(100)

//                _dbState.update {
//                    it.copy(
//                        FourSgpaSRAToastNotifier = false
//                    )
//
//                }

            }
            _dbState.update {
                it.copy(
                    // FourSgpaSRAToastNotifier = false,
                    saveResultAsDialogBoxVisibility = false,
                    saveResultAs = ""
                )
            }

//            viewModelScope.launch {
//
//                delay(1000)
//                _dbState.update {
//                    it.copy(
//                        FourSgpaSRAToastNotifier = false,
//                        // saveResultAsDialogBoxVisibility = false,
//                    )
//                }
//
//            }
            resetFourSgpaSRADBox()


        }




        savedStateHandle.set(DB_STATE_KEY, _dbState.value)


    }


    private fun resetFourSgpaSRADBox() {
        _dbState.update {
            it.copy(
                defaultLabelSRA = FourErrorPassedValues.labelForSRA,
                defaultLabelColourSRA = FourErrorPassedValues.errorPassedColour,
                //saveResultAsDialogBoxVisibility = false,
                //saveResultAs = ""


            )
        }
        savedStateHandle.set(DB_STATE_KEY, _dbState.value)
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
        _dbState.update {
            it.copy(
                courseCode = "",
                selectedCourseUnit = "",
                selectedCourseGrade = "",
                pickedCourseGradeDefaultLabel = FourErrorPassedValues.enterCourseGradeLabel,
                pickedCourseUnitDefaultLabel = FourErrorPassedValues.enterCourseUnitLabel,
                //defaultEnteredCourseCodeLabel = FourErrorPassedValues.enterCourseCodeLabel,

            )

        }
        savedStateHandle.set(DB_STATE_KEY, _dbState.value)

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
            _dbState.update {
                it.copy(
                    greeting = "Good Morning"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            println("Good Morning")
        } else if (afternoon.contains(myHour)) {
            _dbState.update {
                it.copy(
                    greeting = "Good Afternoon"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            println("Good Afternoon")

        } else if (evening.contains(myHour)) {

            _dbState.update {
                it.copy(
                    greeting = "Good Evening"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            println("Good Evening")

        } else if (night.contains(myHour)) {

            _dbState.update {
                it.copy(
                    greeting = "Good Evening"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            println("Good Night")

        }
    }

    private fun GpaDescriptor(gpa: Float, desc: String) {
        if (desc == "sgpa") {
            if (gpa in 3.50..4.00) {
                _dbState.update {
                    it.copy(
                        gpaDescriptor = "Distinction",
                        remark = "You Performed Brilliantly"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            } else if (gpa in 3.00..3.49) {

                _dbState.update {
                    it.copy(
                        gpaDescriptor = "Upper Credit",
                        remark = "You Performed Amazing "
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            } else if (gpa in 2.50..2.99) {

                _dbState.update {
                    it.copy(
                        gpaDescriptor = "Lower Credit",
                        remark = "You Performed Great"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            } else if (gpa in 2.00..2.49) {

                _dbState.update {
                    it.copy(
                        gpaDescriptor = "Pass",
                        remark = "You performed averagely"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            } else if (gpa in 0.00..1.99) {

                _dbState.update {
                    it.copy(
                        gpaDescriptor = "Fail",
                        remark = "You failed"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

        } else if (desc == "cgpa") {

            if (gpa in 3.50..4.00) {
                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Distinction",
                        remark = "You Performed Brilliantly"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            } else if (gpa in 3.00..3.49) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Upper Credit",
                        remark = "You Performed Amazing "
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            } else if (gpa in 2.50..2.99) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Lower Credit",
                        remark = "You Performed Great"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            } else if (gpa in 2.00..2.49) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Pass",
                        remark = "You performed averagely"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            } else if (gpa in 0.00..1.99) {

                _fourCgpaUiState.update {
                    it.copy(
                        gpaDescriptor = "Fail",
                        remark = "You failed"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }


        }


    }

}
