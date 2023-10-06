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
    var defaultEnteredCourseCodeLabel: String = ErrorPassedValues.enterCourseCodeLabel,
    var defaultEditCourseCodeLabel: String = ErrorPassedValues.editCourseCodeLabel,


    var defaultLabelColourETNOC: Long = 0xFFB6B07B,
    var defaultLabelColourETNOCL: Long = 0xFFB6B07B,
    var defaultLabelColourTNOC: Long = 0xFFB6B07B,
    var defaultLabelColourTNOCL: Long = 0xFFB6B07B,
    var defaultLabelColourCC: Long = 0xFFB6B07B,
    var defaultLabelColourECC: Long = 0xFFB6B07B,
    var defaultLabelColourCU: Long = 0xFF888888,
    var defaultLabelColourCG: Long = 0xFF888888,


    var pickedCourseUnitDefaultLabel: String = ErrorPassedValues.enterCourseUnitLabel,
    var pickedCourseGradeDefaultLabel: String = ErrorPassedValues.enterCourseGradeLabel,
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
    var remark: String = "",
    var homeAdShimmerEffectVisibility: Boolean = true,
    var aboutAdShimmerEffectVisibility: Boolean = true,
    var arrayOfAlreadyEnteredCourseslist: ArrayList<String> = ArrayList(),
    var matchAlreadyInCourseEntry: String = "",
    var editedNumberOfCourses: String = "",
    var changeDoneIcon: Boolean = false,

    var errorMessageHolderForETNOCDBToastMessage: String = "",
    var errorToastMessageVisibilityETNOCDB: Boolean = false,
    val maxNoOfCoursesLength: Int = 2,


    ) : Parcelable