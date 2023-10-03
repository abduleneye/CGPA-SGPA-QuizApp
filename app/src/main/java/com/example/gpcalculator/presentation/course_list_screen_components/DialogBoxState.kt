package com.example.gpcalculator.presentation.course_list_screen_components

import android.os.Parcelable
import com.example.gpcalculator.data.ErrorPassedValues


@kotlinx.parcelize.Parcelize
data class DialogBoxState(

    var courseCode: String = "",
    var selectedCourseUnit: String = "",
    var selectedCourseGrade: String = "",
    var totalCourses: String = "",
    var totalCreditLoad: String = "0",
    var enteredCourses: String = "0",
    var isUnitDropDownMenuExpanded: Boolean = false,
    var isGradeDropDownMenuExpanded: Boolean = false,
    var baseEntryDialogBoxVisibility: Boolean = false,
    var resultDialogBoxVisibility: Boolean = false,
    var finalResult: String = "",
    var substituteFinalResult: String = "",
    var dayGreetingState: String = "Good Evening,",


    var defaultLabelTNOC: String = ErrorPassedValues.labelForTNOC,
    var defaultLabelTNOCL: String = ErrorPassedValues.labelForTNOCC,
    var defaultLabelETNOC: String = ErrorPassedValues.labelForETNOC,
    var defaultLabelETNOCL: String = ErrorPassedValues.labelForETNOCC,


//    var defaultLabelColourETNOC: @RawValue Color = Purple200,
//    var defaultLabelColourETNOCL: @RawValue Color = Purple200,
    var defaultEnteredCourseCodeLabel: String = ErrorPassedValues.enterCourseCodeLabel,
//    var defaultLabelColourTNOC: @RawValue Color = Purple200,
//    var defaultLabelColourTNOCL: @RawValue Color = Purple200,


    var pickedCourseUnitLabel: String = ErrorPassedValues.enterCourseUnitLabel,
    var pickedCourseGradeLabel: String = ErrorPassedValues.enterCourseGradeLabel,
    var courseItemsDropDownVisibility: Boolean = false,
    var courseCodeEdited: String = "",
    var selectedCourseUnitEdited: String = "",
    var selectedCourseGradeEdited: String = "",
    var courseEntryIndex: String = "0",
    var courseEntryDialogBoxVisibility: Boolean = false,
    var editBaseEntryDialogBoxVisibility: Boolean = false,
    var courseEntryEditDialogBoxVisibility: Boolean = false,
    var allReadyInList: Boolean = false,
    var editedNmberOfCoursesHolder: String = "",
    var prevTotalNumberOfCourses: String = "",
    var clearCoursesConfirmationDBoxVisibility: Boolean = false,
    var greeting: String = "",
    var gpaDescriptor: String = "",
    var remark: String = ""


) : Parcelable