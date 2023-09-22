package com.example.gpcalculator.presentation.course_list_screen_components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gpcalculator.data.ErrorPassedValues
import com.example.gpcalculator.ui.theme.Purple200

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


    var defaultLabelTNOC: String = ErrorPassedValues.labelForTNOC,
    var defaultLabelTNOCL: String = ErrorPassedValues.labelForTNOCC,
    var defaultLabelETNOC: String = ErrorPassedValues.labelForETNOC,
    var defaultLabelETNOCL: String = ErrorPassedValues.labelForETNOCC,

    var defaultLabelColourETNOC: Color = Purple200,
    var defaultLabelColourETNOCL: Color = Purple200,
    var defaultEnteredCourseCodeLabel: String = ErrorPassedValues.enterCourseCodeLabel,
    var defaultLabelColourTNOC: Color = Purple200,
    var defaultLabelColourTNOCL: Color = Purple200,


    var pickedCourseUnitLabel: String = ErrorPassedValues.enterCourseUnitLabel,
    var pickedCourseGradeLabel: String = ErrorPassedValues.enterCourseGradeLabel,
    var dialogDefaultHeight: Dp = 300.dp,
    var courseItemsDropDownVisibility: Boolean = false,
    var courseCodeEdited: String = "",
    var selectedCourseUnitEdited: String = "",
    var selectedCourseGradeEdited: String = "",
    var courseEntryIndex: String = "0",
    var courseEntryDialogBoxVisibility: Boolean = false,
    var editBaseEntryDialogBoxVisibility: Boolean = false,
    var courseEntryEditDialogBoxVisibility: Boolean = false,
    var allReadyInList: Boolean = false


)