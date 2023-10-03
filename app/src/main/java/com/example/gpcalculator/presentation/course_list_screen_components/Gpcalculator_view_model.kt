package com.example.gpcalculator.presentation.course_list_screen_components

import GpCalculatorPrototype.Data.CourseDataEntries
import GpCalculatorPrototype.Data.CourseMaps
import GpCalculatorPrototype.Data.CoursesUnitPointArrayList
import GpCalculatorPrototype.Data.GpData
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.gpcalculator.data.ErrorMessages
import com.example.gpcalculator.data.ErrorPassedValues
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale

class gpcalculator_view_model(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        private const val DB_STATE_KEY = "my_db_state"
        private const val COURSE_ENTRIES_KEY = "my_course_entry_state"
    }

    private val coursePointObj = CoursesUnitPointArrayList()
    private val courseMapObj = CourseMaps()
    private val coursesDataEntryObj = CourseDataEntries()
    private val stateClassObject = DialogBoxState()
    private var result = ""
    var coursesUnitSubList = ArrayList<Int>()


    private var _courseEntries = MutableStateFlow(
        savedStateHandle.get(COURSE_ENTRIES_KEY) ?: CourseDataEntries().coursesDataEntry
    )
    var courseEntries = _courseEntries.asStateFlow()

    private var _dbState = MutableStateFlow(savedStateHandle.get(DB_STATE_KEY) ?: DialogBoxState())
    var dbState = _dbState.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: DialogBoxUiEvents) {

        when (event) {

            is DialogBoxUiEvents.replaceEditedInEntriesToArrayList -> {
                textFieldsErrorCheckEditedCourseDataEntry()
            }

            is DialogBoxUiEvents.editItemsEntries -> {

                _dbState.update {
                    it.copy(
                        courseCode = event.courseCodeEdit,
                        selectedCourseUnit = event.unitEdit,
                        selectedCourseGrade = event.gradeEdit

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.updateCourseIndexEntry -> {
                _dbState.update {
                    it.copy(
                        courseEntryIndex = event.entryIndex
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.showCourseEntryEditDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideCourseEntryEditDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryEditDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }


            is DialogBoxUiEvents.resetResultField -> {

                _dbState.update {
                    it.copy(
                        finalResult = "new val"
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.showDataEntryDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is DialogBoxUiEvents.hideDataEntryDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.showUnitMenuDropDown -> {

                _dbState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is DialogBoxUiEvents.hideUnitMenuDropDown -> {

                _dbState.update {
                    it.copy(
                        isUnitDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is DialogBoxUiEvents.showGradeMenuDropDown -> {
                _dbState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideGradeMenuDropDown -> {
                _dbState.update {
                    it.copy(
                        isGradeDropDownMenuExpanded = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.setSelectedCourseGrade -> {

                _dbState.update {
                    it.copy(
                        selectedCourseGrade = event.grade
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is DialogBoxUiEvents.setSelectedCourseUnit -> {
                _dbState.update {
                    it.copy(
                        selectedCourseUnit = event.unit
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.deleteCourseEntry -> {

                _courseEntries.value.removeAt(event.itemToRemove)
                savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)
                _dbState.update {
                    it.copy(
                        enteredCourses = _courseEntries.value.size.toString()
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

//                _courseEntries.value.add(
//                    GpData(
//                        _dbState.value.courseCode.uppercase(Locale.UK),
//                        _dbState.value.selectedCourseGrade,
//                        _dbState.value.selectedCourseUnit.toInt()
//                    ))
            }

            is DialogBoxUiEvents.setCourseCode -> {

                // if (event.toString().contains("")) {
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

            is DialogBoxUiEvents.addEntriesToArrayList -> {

                textFieldsErrorCheckCourseDataEntry()

            }

            is DialogBoxUiEvents.setTotalCreditLoad -> {
                _dbState.update {
                    it.copy(
                        totalCreditLoad = event.totalCreditLoad
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.setTotalCourses -> {
                _dbState.update {
                    it.copy(
                        totalCourses = event.totalCourses,
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideBaseEntryDBox -> {
                textFieldsErrorCheckBaseEntryDB()

            }

            is DialogBoxUiEvents.showResultDBox -> {
                _dbState.update {
                    it.copy(
                        resultDialogBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideResultDBox -> {
                _dbState.update {

                    it.copy(
                        resultDialogBoxVisibility = false,
                        finalResult = ""

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.resetAlreadyInList -> {
                _dbState.update {
                    it.copy(
                        allReadyInList = false
                    )

                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is DialogBoxUiEvents.showCourseDataEntriesContextmenu -> {

                _dbState.update {
                    it.copy(
                        courseItemsDropDownVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


            }

            is DialogBoxUiEvents.hideCourseDataEntriesContextmenu -> {
                _dbState.update {
                    it.copy(
                        courseItemsDropDownVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.resetTotalEntries -> {


                if (_dbState.value.totalCourses > 0.toString()) {

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

            is DialogBoxUiEvents.resetDefaultValuesFromErrorsTNOC -> {
                _dbState.update {
                    it.copy(
                        //defaultLabelColourTNOC = ErrorPassedValues.errorPassedColour,
                        defaultLabelTNOC = ErrorPassedValues.labelForTNOC

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.resetDefaultValuesFromErrorsTNOCL -> {
                _dbState.update {
                    it.copy(
                        defaultLabelTNOCL = ErrorPassedValues.labelForTNOCC,
                        //defaultLabelColourTNOCL = ErrorPassedValues.errorPassedColour

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.showEditBaseEntryDBox -> {
                _dbState.update {
                    it.copy(
                        editBaseEntryDialogBoxVisibility = true

                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideEditBaseEntryDBox -> {

                _dbState.update {
                    it.copy(
                        editBaseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideEditBaseEntryRegardlessDBox -> {

                _dbState.update {
                    it.copy(
                        editBaseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }


            is DialogBoxUiEvents.showBaseEntryDBox -> {

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


            is DialogBoxUiEvents.executeCalculation -> {
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
                        finalResult = result
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)


                GpaDescriptor(_dbState.value.finalResult.toFloat())

                onReExecuteCalculationClearArrayField()


            }

            is DialogBoxUiEvents.hideBaseEntryRegardlessDBox -> {
                _dbState.update {
                    it.copy(
                        baseEntryDialogBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.setPrevTotalCourses -> {
                _dbState.update {
                    it.copy(
                        prevTotalNumberOfCourses = event.text
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.showClearConfirmationDBox -> {
                _dbState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = true
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }

            is DialogBoxUiEvents.hideClearConfirmationDBox -> {
                _dbState.update {
                    it.copy(
                        clearCoursesConfirmationDBoxVisibility = false
                    )
                }
                savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            }


            else -> {}
        }

    }


    private fun textFieldsErrorCheckBaseEntryDB() {

        if (_dbState.value.totalCourses.isEmpty()) {

            _dbState.update {
                it.copy(
                    defaultLabelTNOC = ErrorMessages.errorLabelMessageForTNOC,
                    //defaultLabelColourTNOC = ErrorMessages.textFieldErrorLabelColor
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

        if (_dbState.value.totalCourses.isEmpty()) {

            _dbState.update {
                it.copy(
                    defaultLabelTNOC = ErrorMessages.errorLabelMessageForTNOC,
                    //defaultLabelColourTNOC = ErrorMessages.textFieldErrorLabelColor
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        }
//        else if (_dbState.value.totalCreditLoad.isEmpty()) {
//            _dbState.update {
//                it.copy(
//                    defaultLabelTNOCL = ErrorMessages.errorLabelMessageForTNOCL,
//                    defaultLabelColourTNOCL = ErrorMessages.textFieldErrorLabelColor
//
//                )
//            }
//
//        }
        else {

            _dbState.update {
                it.copy(
                    baseEntryDialogBoxVisibility = false
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        }


    }


    private fun textFieldsErrorCheckEditedCourseDataEntry() {

        if (_dbState.value.courseCode.isEmpty()) {

            _dbState.update {
                it.copy(
                    //defaultEnteredCourseCodeLabel = ErrorMessages.errorMessageForCourseCode
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.selectedCourseUnit.isEmpty()) {
            _dbState.update {
                it.copy(
                    pickedCourseUnitLabel = ErrorMessages.errorMessageForCourseUnit

                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.selectedCourseGrade.isEmpty()) {

            _dbState.update {
                it.copy(
                    pickedCourseGradeLabel = ErrorMessages.errorMessageForCourseGrade
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_courseEntries.value.contains(
                GpData(
                    _dbState.value.courseCode.uppercase(Locale.UK),
                    _dbState.value.selectedCourseGrade,
                    _dbState.value.selectedCourseUnit.toInt()
                )
            )
        ) {

            _dbState.update {
                it.copy(
                    allReadyInList = true
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            _dbState.update {
                it.copy(
                    allReadyInList = false
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else {

            //RemoveSpace Regardless
//            _dbState.update {
//                it.copy(
//                    courseCode = courseCode.replace(" ", "")
//                )
//            }
            _courseEntries.value[_dbState.value.courseEntryIndex.toInt()] = GpData(
                courseCode = _dbState.value.courseCode.uppercase(),
                courseGrade = _dbState.value.selectedCourseGrade,
                courseUnit = _dbState.value.selectedCourseUnit.toInt()
            )
            savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)


            _dbState.update {
                it.copy(
                    enteredCourses = _courseEntries.value.size.toString(),
                    courseEntryDialogBoxVisibility = false,

                    )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            clearCourseDataEntry()


        }


    }


    private fun textFieldsErrorCheckCourseDataEntry() {

        if (_dbState.value.courseCode.isEmpty()) {

            _dbState.update {
                it.copy(
                    //defaultEnteredCourseCodeLabel = ErrorMessages.errorMessageForCourseCode
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.selectedCourseUnit == "") {
            _dbState.update {
                it.copy(
                    pickedCourseUnitLabel = ErrorMessages.errorMessageForCourseUnit

                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_dbState.value.selectedCourseGrade == "") {

            _dbState.update {
                it.copy(
                    pickedCourseGradeLabel = ErrorMessages.errorMessageForCourseGrade
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (_courseEntries.value.contains(
                GpData(
                    _dbState.value.courseCode.uppercase(Locale.UK),
                    _dbState.value.selectedCourseGrade,
                    _dbState.value.selectedCourseUnit.toInt()
                )
            )
        ) {
            savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)


            _dbState.update {
                it.copy(
                    allReadyInList = true
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else {
            _dbState.update {
                it.copy(
                    allReadyInList = false
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)



            _courseEntries.value.add(
                GpData(
                    _dbState.value.courseCode.uppercase(Locale.UK),
                    _dbState.value.selectedCourseGrade,
                    _dbState.value.selectedCourseUnit.toInt()
                )
            )
            savedStateHandle.set(COURSE_ENTRIES_KEY, _courseEntries.value)


            _dbState.update {
                it.copy(
                    enteredCourses = _courseEntries.value.size.toString(),
                    courseEntryDialogBoxVisibility = false,

                    )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

            clearCourseDataEntry()


        }


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

    private fun courseValueMapper(courseGrade: ArrayList<GpData>) {


        courseGrade.forEach { courseData ->

            when (courseData) {

                //For six unit Courses

                GpData(courseCode = courseData.courseCode, courseGrade = "A", courseUnit = 6) -> {

                    courseMapObj.sixUnit_GradeMap["A"]?.let { coursePointObj.sixUnitA.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "B", courseUnit = 6) -> {

                    courseMapObj.sixUnit_GradeMap["B"]?.let { coursePointObj.sixUnitB.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "C", courseUnit = 6) -> {

                    courseMapObj.sixUnit_GradeMap["C"]?.let { coursePointObj.sixUnitC.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "D", courseUnit = 6) -> {

                    courseMapObj.sixUnit_GradeMap["D"]?.let { coursePointObj.sixUnitD.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "E", courseUnit = 6) -> {

                    courseMapObj.sixUnit_GradeMap["E"]?.let { coursePointObj.sixUnitE.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "F", courseUnit = 6) -> {

                    courseMapObj.sixUnit_GradeMap["F"]?.let { coursePointObj.sixUnitF.add(it) }

                }


                //For four unit Courses

                GpData(courseCode = courseData.courseCode, courseGrade = "A", courseUnit = 4) -> {

                    courseMapObj.fourUnit_GradeMap["A"]?.let { coursePointObj.fourUnitA.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "B", courseUnit = 4) -> {

                    courseMapObj.fourUnit_GradeMap["B"]?.let { coursePointObj.fourUnitB.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "C", courseUnit = 4) -> {

                    courseMapObj.fourUnit_GradeMap["C"]?.let { coursePointObj.fourUnitC.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "D", courseUnit = 4) -> {

                    courseMapObj.fourUnit_GradeMap["D"]?.let { coursePointObj.fourUnitD.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "E", courseUnit = 4) -> {

                    courseMapObj.fourUnit_GradeMap["E"]?.let { coursePointObj.fourUnitE.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "F", courseUnit = 4) -> {

                    courseMapObj.fourUnit_GradeMap["F"]?.let { coursePointObj.fourUnitF.add(it) }

                }


                //For three unit Courses

                GpData(courseCode = courseData.courseCode, courseGrade = "A", courseUnit = 3) -> {

                    courseMapObj.threeUnit_GradeMap["A"]?.let { coursePointObj.threeUnitA.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "B", courseUnit = 3) -> {

                    courseMapObj.threeUnit_GradeMap["B"]?.let { coursePointObj.threeUnitB.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "C", courseUnit = 3) -> {

                    courseMapObj.threeUnit_GradeMap["C"]?.let { coursePointObj.threeUnitC.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "D", courseUnit = 3) -> {

                    courseMapObj.threeUnit_GradeMap["D"]?.let { coursePointObj.threeUnitD.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "E", courseUnit = 3) -> {

                    courseMapObj.threeUnit_GradeMap["E"]?.let { coursePointObj.threeUnitE.add(it) }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "F", courseUnit = 3) -> {

                    courseMapObj.threeUnit_GradeMap["F"]?.let { coursePointObj.threeUnitF.add(it) }

                }


                //For two unit courses:

                GpData(courseCode = courseData.courseCode, "A", 2) -> {

                    courseMapObj.twoUnit_GradeMap["A"]?.let {

                        coursePointObj.twoUnitA.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, "B", 2) -> {

                    courseMapObj.twoUnit_GradeMap["B"]?.let {

                        coursePointObj.twoUnitB.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, "C", 2) -> {

                    courseMapObj.twoUnit_GradeMap["C"]?.let {

                        coursePointObj.twoUnitC.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, "D", 2) -> {

                    courseMapObj.twoUnit_GradeMap["D"]?.let {

                        coursePointObj.twoUnitD.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, "E", 2) -> {

                    courseMapObj.twoUnit_GradeMap["E"]?.let {

                        coursePointObj.twoUnitE.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, "A", 2) -> {

                    courseMapObj.twoUnit_GradeMap["F"]?.let {

                        coursePointObj.twoUnitF.add(it)
                    }

                }

                //For one unit Courses

                GpData(courseCode = courseData.courseCode, courseGrade = "A", courseUnit = 1) -> {

                    courseMapObj.oneUnit_GradeMap["A"]?.let {
                        coursePointObj.oneUnitA.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "B", courseUnit = 1) -> {

                    courseMapObj.oneUnit_GradeMap["B"]?.let {
                        coursePointObj.oneUnitB.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "C", courseUnit = 1) -> {

                    courseMapObj.oneUnit_GradeMap["C"]?.let {
                        coursePointObj.oneUnitC.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "D", courseUnit = 1) -> {

                    courseMapObj.oneUnit_GradeMap["D"]?.let {
                        coursePointObj.oneUnitD.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "E", courseUnit = 1) -> {

                    courseMapObj.oneUnit_GradeMap["E"]?.let {
                        coursePointObj.oneUnitE.add(it)
                    }

                }

                GpData(courseCode = courseData.courseCode, courseGrade = "F", courseUnit = 1) -> {

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
                pickedCourseGradeLabel = ErrorPassedValues.enterCourseGradeLabel,
                pickedCourseUnitLabel = ErrorPassedValues.enterCourseUnitLabel,
                //defaultEnteredCourseCodeLabel = ErrorPassedValues.enterCourseCodeLabel,

            )

        }
        savedStateHandle.set(DB_STATE_KEY, _dbState.value)

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

    private fun GpaDescriptor(gpa: Float) {
        if (gpa in 4.50..5.00) {
            _dbState.update {
                it.copy(
                    gpaDescriptor = "First Class",
                    remark = "You Performed Brilliantly"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)

        } else if (gpa in 3.50..4.49) {

            _dbState.update {
                it.copy(
                    gpaDescriptor = "Second Class Upper",
                    remark = "You Performed Amazing "
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (gpa in 2.40..3.49) {

            _dbState.update {
                it.copy(
                    gpaDescriptor = "Second Class Lower",
                    remark = "You Performed Great"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (gpa in 1.50..2.39) {

            _dbState.update {
                it.copy(
                    gpaDescriptor = "Third Class",
                    remark = "You performed averagely"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (gpa in 1.00..1.49) {

            _dbState.update {
                it.copy(
                    gpaDescriptor = "Pass",
                    remark = "You passed"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        } else if (gpa in 0.00..1.00) {

            _dbState.update {
                it.copy(
                    gpaDescriptor = "Failure",
                    remark = "You Failed"
                )
            }
            savedStateHandle.set(DB_STATE_KEY, _dbState.value)


        }

    }


}