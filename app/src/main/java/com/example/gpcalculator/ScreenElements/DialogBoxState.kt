package com.example.gpcalculator.ScreenElements

import com.example.gpcalculator.Data.ErrorPassedValues

data class DialogBoxState(

    val courseCode: String = "",
    val selectedCourseUnit: String = "",
    val selectedCourseGrade: String = "",
    val totalCourses: String  = "",
    val totalCreditLoad: String = "",
    val enteredCourses: String = "0",
    val isUnitDropDownMenuExpanded: Boolean = false,
    val isGradeDropDownMenuExpanded: Boolean = false,
    var courseEntryDialogBoxVisibility:  Boolean  = false,
    var baseEntryDialogBoxVisibility:  Boolean  = true,
    var resultDialogBoxVisibility:  Boolean  = false,
    var finalResult: String = "",
    var substituteFinalResult: String = "",
    var totalCourseslabel: String = ErrorPassedValues.enterTotalCoursesLabel,
    var totalCreditLoadLabel: String = ErrorPassedValues.enterTotalCreditLoadLabel,
    var enteredCourseCodeLabel: String = ErrorPassedValues.enterCourseCodeLabel,
    var pickedCourseUnitLabel: String  = ErrorPassedValues.enterCourseUnitLabel,
    var pickedCourseGradeLabel: String = ErrorPassedValues.enterCourseGradeLabel




)