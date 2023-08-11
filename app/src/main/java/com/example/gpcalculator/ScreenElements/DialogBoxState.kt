package com.example.gpcalculator.ScreenElements

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gpcalculator.Data.ErrorPassedValues

data class DialogBoxState(

    var courseCode: String = "",
    var selectedCourseUnit: String = "",
    var selectedCourseGrade: String = "",
    var totalCourses: String = "",
    var totalCreditLoad: String = "",
    var enteredCourses: String = "0",
    var isUnitDropDownMenuExpanded: Boolean = false,
    var isGradeDropDownMenuExpanded: Boolean = false,
    var baseEntryDialogBoxVisibility: Boolean = true,
    var resultDialogBoxVisibility: Boolean = false,
    var finalResult: String = "",
    var substituteFinalResult: String = "",
    var totalCourseslabel: String = ErrorPassedValues.enterTotalCoursesLabel,
    var totalCreditLoadLabel: String = ErrorPassedValues.enterTotalCreditLoadLabel,
    var enteredCourseCodeLabel: String = ErrorPassedValues.enterCourseCodeLabel,
    var pickedCourseUnitLabel: String = ErrorPassedValues.enterCourseUnitLabel,
    var pickedCourseGradeLabel: String = ErrorPassedValues.enterCourseGradeLabel,
    var dialogDefaultHeight: Dp = 300.dp,
    var courseItemsDropDownVisibility: Boolean = false,
    var courseCodeEdited: String = "",
    var selectedCourseUnitEdited: String = "",
    var selectedCourseGradeEdited: String = "",
    var courseEntryIndex: String = "0",
    var courseEntryDialogBoxVisibility: Boolean = false,
    var courseEntryEditDialogBoxVisibility: Boolean = false,


    )