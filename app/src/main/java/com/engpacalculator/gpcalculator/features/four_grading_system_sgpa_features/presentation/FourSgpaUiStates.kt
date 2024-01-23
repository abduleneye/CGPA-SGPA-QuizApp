package com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.FourErrorPassedValues


@kotlinx.parcelize.Parcelize
data class FourSgpaUiStates(

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
    var fourSgpaFinalResult: String = "",
    var substituteFinalResult: String = "",
    var dayGreetingState: String = "Good Evening,",


    var defaultLabelTNOC: String = FourErrorPassedValues.labelForTNOC,
    var defaultLabelTNOCL: String = FourErrorPassedValues.labelForTNOCC,
    var defaultLabelETNOC: String = FourErrorPassedValues.labelForETNOC,
    var defaultLabelETNOCL: String = FourErrorPassedValues.labelForETNOCC,
    var defaultEnteredCourseCodeLabel: String = FourErrorPassedValues.enterCourseCodeLabel,
    var defaultEditCourseCodeLabel: String = FourErrorPassedValues.editCourseCodeLabel,
    var defaultLabelSRA: String = FourErrorPassedValues.labelForSRA,


    var defaultLabelColourETNOC: Long = 0xFFB6B07B,
    var defaultLabelColourETNOCL: Long = 0xFFB6B07B,
    var defaultLabelColourTNOC: Long = 0xFFB6B07B,
    var defaultLabelColourTNOCL: Long = 0xFFB6B07B,
    var defaultLabelColourCC: Long = 0xFFB6B07B,
    var defaultLabelColourECC: Long = 0xFFB6B07B,
    var defaultLabelColourCU: Long = 0xFF888888,
    var defaultLabelColourCG: Long = 0xFF888888,
    val defaultLabelColourSRA: Long = 0xFFB6B07B,


    var pickedCourseUnitDefaultLabel: String = FourErrorPassedValues.enterCourseUnitLabel,
    var pickedCourseGradeDefaultLabel: String = FourErrorPassedValues.enterCourseGradeLabel,
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
    var greeting: String = "Good Day",
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
    val sameValHolder: String = "",
    val allReadyInListForEditCourseEntries: Boolean = false,
    val saveResultAs: String = "",
    var saveResultAsDialogBoxVisibility: Boolean = false,
    var fourSgpaSRAToastNotifier: Boolean = false


) : Parcelable