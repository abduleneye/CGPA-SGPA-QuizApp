package com.example.gpcalculator.myViewModels

import GpCalculatorPrototype.Data.CourseDataEntries
import GpCalculatorPrototype.Data.CourseMaps
import GpCalculatorPrototype.Data.CoursesUnitPointArrayList
import GpCalculatorPrototype.Data.GpData
import androidx.lifecycle.ViewModel
import com.example.gpcalculator.Data.ErrorMessages
import com.example.gpcalculator.Data.ErrorPassedValues
import com.example.gpcalculator.ScreenElements.DialogBoxState
import com.example.gpcalculator.ScreenElements.DialogBoxUiEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import kotlin.collections.ArrayList

class FirstViewModel:ViewModel() {

    private val coursePointObj = CoursesUnitPointArrayList()
    private val courseMapObj = CourseMaps()
    private val coursesDataEntryObj  = CourseDataEntries()
    private val stateClassObject  = DialogBoxState()
    private var result  = ""

    private var _courseEntries = MutableStateFlow(CourseDataEntries().coursesDataEntry)
    var courseEntries = _courseEntries.asStateFlow()

    private  var _dbState = MutableStateFlow(DialogBoxState())
          var dbState = _dbState.asStateFlow()


    fun onEvent(event: DialogBoxUiEvents){

        when(event){

            is DialogBoxUiEvents.resetResultField -> {

                _dbState.update {
                    it.copy(
                        finalResult = "new val"
                    )
                }
            }

            is DialogBoxUiEvents.showDataEntryDBox  ->  {

               _dbState.update {
                   it.copy(
                       courseEntryDialogBoxVisibility = true
                   )
               }

            }

            is DialogBoxUiEvents.hideDataEntryDBox -> {
                _dbState.update {
                    it.copy(
                        courseEntryDialogBoxVisibility = false
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

            is DialogBoxUiEvents.addEntriesToArrayList -> {

                textFieldsErrorCheckCourseDataEntry()

            }

            is DialogBoxUiEvents.setTotalCreditLoad -> {
                _dbState.update {
                    it.copy(totalCreditLoad = event.totalCreditLoad.toInt().toString())
                }
            }

            is DialogBoxUiEvents.setTotalCourses -> {
                _dbState.update {
                    it.copy(
                        totalCourses = event.totalCourses
                    )
                }
            }

            is DialogBoxUiEvents.hideBaseEntryDBox -> {
                textFieldsErrorCheckBaseEntryDB()

            }

            is DialogBoxUiEvents.executeCalculation -> {

//                _dbState.update {
//                    it.copy(
//                        resultDialogBoxVisibility = true
//                    )
//                }



                    courseValueMapper(_courseEntries.value)
                     result = operations(_dbState.value.totalCreditLoad.toInt())
                    _dbState.update {
                        it.copy(
                            finalResult = result
                        )
                    }

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





            }

            is DialogBoxUiEvents.showResultDBox -> {
                _dbState.update {
                    it.copy(
                        resultDialogBoxVisibility = true
                    )
                }
            }

            is DialogBoxUiEvents.hideResultDBox -> {
                _dbState.update {

                    it.copy(
                        resultDialogBoxVisibility = false,
                        finalResult = ""

                    )
                }


            }

            else -> {}
        }

    }




    private fun textFieldsErrorCheckBaseEntryDB(){

        if (_dbState.value.totalCourses  == ""){

            _dbState.update {
                it.copy(
                    totalCourseslabel = ErrorMessages.errorMessageForTotalNumberOFCourses
                )
            }




        }
        else if(_dbState.value.totalCreditLoad == ""){
            _dbState.update {
                it.copy(
                    totalCreditLoadLabel = ErrorMessages.errorMessageForTotalNumberOFCreditLoad

                )
            }

        }
        else{

            _dbState.update {
                it.copy(
                    baseEntryDialogBoxVisibility = false
                )
            }

        }


    }

    private fun textFieldsErrorCheckCourseDataEntry(){

        if (_dbState.value.courseCode == "" ){

            _dbState.update {
                it.copy(
                    enteredCourseCodeLabel = ErrorMessages.errorMessageForCourseCode
                )
            }




        }
        else if(_dbState.value.selectedCourseUnit == ""){
            _dbState.update {
                it.copy(
                    pickedCourseUnitLabel = ErrorMessages.errorMessageForCourseUnit

                )
            }

        }

        else if(_dbState.value.selectedCourseGrade  == ""){

            _dbState.update {
                it.copy(
                    pickedCourseGradeLabel = ErrorMessages.errorMessageForCourseGrade
                )
            }

        }
        else{

            _courseEntries.value.add(
                GpData(
                    _dbState.value.courseCode.uppercase(Locale.UK),
                    _dbState.value.selectedCourseGrade,
                    _dbState.value.selectedCourseUnit.toInt()
                ))

            _dbState.update {
                it.copy(
                    enteredCourses = _courseEntries.value.size.toString(),
                    courseEntryDialogBoxVisibility = false,

                )
            }
            clearCourseDataEntry()


        }



    }




    private fun operations(totalCreditLoad: Int): String {

        var finalAns: Int  = 0



        coursesDataEntryObj.threeUnitCoursesPointSum = coursePointObj.threeUnitA.sum() + coursePointObj.threeUnitB.sum() + coursePointObj.threeUnitC.sum() + coursePointObj.threeUnitD.sum() + coursePointObj.threeUnitE.sum() + coursePointObj.threeUnitF.sum()
        coursesDataEntryObj.twoUnitCoursesPointSum  = coursePointObj.twoUnitA.sum() + coursePointObj.twoUnitB.sum() + coursePointObj.twoUnitC.sum() + coursePointObj.twoUnitD.sum() + coursePointObj.twoUnitE.sum() + coursePointObj.twoUnitF.sum()
        coursesDataEntryObj.oneUnitCoursesPointSum = coursePointObj.oneUnitA.sum() + coursePointObj.oneUnitB.sum() + coursePointObj.oneUnitC.sum() + coursePointObj.oneUnitD.sum() + coursePointObj.oneUnitE.sum() + coursePointObj.oneUnitF.sum()
        coursesDataEntryObj.totalCoursesPointSum = (coursesDataEntryObj.threeUnitCoursesPointSum + coursesDataEntryObj.twoUnitCoursesPointSum + coursesDataEntryObj.oneUnitCoursesPointSum).toDouble()

         finalAns  = (coursesDataEntryObj.totalCoursesPointSum  /  totalCreditLoad).toInt()

        return ("Your Gp is: $finalAns")
    }

    private fun courseValueMapper(courseGrade: ArrayList<GpData>){


        courseGrade.forEach { courseData ->

            when(courseData){

                //For three unit Courses

                GpData(courseCode = courseData.courseCode ,courseGrade = "A", courseUnit = 3) ->{

                    courseMapObj.threeUnit_GradeMap["A"]?.let { coursePointObj.threeUnitA.add(it) }

                }
                GpData(courseCode = courseData.courseCode ,courseGrade = "B", courseUnit = 3) ->{

                    courseMapObj.threeUnit_GradeMap["B"]?.let { coursePointObj.threeUnitB.add(it) }

                }
                GpData(courseCode = courseData.courseCode ,courseGrade = "C", courseUnit = 3) ->{

                    courseMapObj.threeUnit_GradeMap["C"]?.let { coursePointObj.threeUnitC.add(it) }

                }
                GpData(courseCode = courseData.courseCode ,courseGrade = "D", courseUnit = 3) ->{

                    courseMapObj.threeUnit_GradeMap["D"]?.let { coursePointObj.threeUnitD.add(it) }

                }
                GpData(courseCode = courseData.courseCode ,courseGrade = "E", courseUnit = 3) ->{

                    courseMapObj.threeUnit_GradeMap["E"]?.let { coursePointObj.threeUnitE.add(it) }

                }
                GpData(courseCode = courseData.courseCode ,courseGrade = "F", courseUnit = 3) ->{

                    courseMapObj.threeUnit_GradeMap["F"]?.let { coursePointObj.threeUnitF.add(it) }

                }


                //For two unit courses:

                GpData(courseCode = courseData.courseCode, "A", 2)  -> {

                    courseMapObj.twoUnit_GradeMap["A"]?.let {

                        coursePointObj.twoUnitA.add(it)
                    }

                } GpData(courseCode = courseData.courseCode, "B", 2)  -> {

                courseMapObj.twoUnit_GradeMap["B"]?.let {

                    coursePointObj.twoUnitB.add(it)
                }

            } GpData(courseCode = courseData.courseCode, "C", 2)  -> {

                courseMapObj.twoUnit_GradeMap["C"]?.let {

                    coursePointObj.twoUnitC.add(it)
                }

            } GpData(courseCode = courseData.courseCode, "D", 2)  -> {

                courseMapObj.twoUnit_GradeMap["D"]?.let {

                    coursePointObj.twoUnitD.add(it)
                }

            } GpData(courseCode = courseData.courseCode, "E", 2)  -> {

                courseMapObj.twoUnit_GradeMap["E"]?.let {

                    coursePointObj.twoUnitE.add(it)
                }

            } GpData(courseCode = courseData.courseCode, "A", 2)  -> {

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

    private fun clearCourseDataEntry(){
        _dbState.update {
            it.copy(
                courseCode = "",
                selectedCourseUnit = "",
                selectedCourseGrade = "",
                pickedCourseGradeLabel = ErrorPassedValues.enterCourseGradeLabel,
                pickedCourseUnitLabel = ErrorPassedValues.enterCourseUnitLabel,
                enteredCourseCodeLabel = ErrorPassedValues.enterCourseCodeLabel ,

            )
        }
    }

    private fun clearResultEntry(){
        _dbState.update {
            it.copy(finalResult = "")
        }
    }

}