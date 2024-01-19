package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation

import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity

sealed interface FiveGpaUiEvents {
    object showUnitMenuDropDown : FiveGpaUiEvents
    object hideUnitMenuDropDown : FiveGpaUiEvents
    object showGradeMenuDropDown : FiveGpaUiEvents
    object hideGradeMenuDropDown : FiveGpaUiEvents

    object resetResultField : FiveGpaUiEvents

    object showCourseDataEntriesContextmenu : FiveGpaUiEvents

    object hideCourseDataEntriesContextmenu : FiveGpaUiEvents

    data class setSelectedCourseGrade(var grade: String) : FiveGpaUiEvents
    data class setSelectedCourseUnit(var unit: String) : FiveGpaUiEvents
    data class setCourseCode(var courseCode: String) : FiveGpaUiEvents

    data class setTotalCourses(var totalCourses: String) : FiveGpaUiEvents

    data class setTotalCreditLoad(var totalCreditLoad: String) : FiveGpaUiEvents

    data class deleteCourseEntry(var itemToRemove: Int) : FiveGpaUiEvents

    data class editItemsEntries(
        var courseCodeEdit: String,
        var gradeEdit: String,
        var unitEdit: String
    ) : FiveGpaUiEvents

    data class updateCourseIndexEntry(var entryIndex: String) : FiveGpaUiEvents

    object showDataEntryDBox : FiveGpaUiEvents
    object hideDataEntryDBox : FiveGpaUiEvents
    object showBaseEntryDBox : FiveGpaUiEvents
    object showResultDBox : FiveGpaUiEvents
    object addEntriesToArrayList : FiveGpaUiEvents

    object replaceEditedInEntriesToArrayList : FiveGpaUiEvents


    object executeCalculation : FiveGpaUiEvents

    object resetTotalEntries : FiveGpaUiEvents

    object showCourseEntryEditDBox : FiveGpaUiEvents
    object hideCourseEntryEditDBox : FiveGpaUiEvents
    object resetAlreadyInList : FiveGpaUiEvents
    object resetBackToDefaultValuesFromErrorsTNOC : FiveGpaUiEvents
    object resetDefaultValuesFromErrorsTNOCL : FiveGpaUiEvents
    object resetDefaultLabelTextTNOCL : FiveGpaUiEvents
    object showEditBaseEntryDBox : FiveGpaUiEvents
    object hideEditBaseEntryRegardlessDBox : FiveGpaUiEvents
    object hideBaseEntryRegardlessDBox : FiveGpaUiEvents
    object hideBaseEntryDBox : FiveGpaUiEvents
    object hideEditBaseEntryDBox : FiveGpaUiEvents
    object hideClearConfirmationDBox : FiveGpaUiEvents
    object showClearConfirmationDBox : FiveGpaUiEvents
    object showSaveResultDBox : FiveGpaUiEvents
    object hideFiveSgpaSaveResultDB : FiveGpaUiEvents


    data class dropDownAssumeText(val text: String) : FiveGpaUiEvents
    data class setPrevTotalCourses(val text: String) : FiveGpaUiEvents

    object hideHomeAdShimmerEffect : FiveGpaUiEvents
    object showHomeAdShimmerEffect : FiveGpaUiEvents
    object hideAboutAdShimmerEffect : FiveGpaUiEvents
    object showAboutAdShimmerEffect : FiveGpaUiEvents
    object resetBackToDefaultValuesFromErrorsCC : FiveGpaUiEvents
    object resetBackToDefaultValuesFromErrorsCU : FiveGpaUiEvents
    object resetBackToDefaultValuesFromErrorsCG : FiveGpaUiEvents
    object resetBackToDefaultValuesFromErrorsECC : FiveGpaUiEvents
    object resetBackToDefaultValueFromErrorSRA : FiveGpaUiEvents
    object saveFiveSgpaResult : FiveGpaUiEvents
    object loadResult : FiveGpaUiEvents
    object hideSaveResultsFiveGpa : FiveGpaUiEvents
    object executeCgpaCalculation : FiveGpaUiEvents

    data class setTotalNumberOfEditedCourses(val noOfEditedTotalCourse: String) :
        FiveGpaUiEvents

    data class setSRA(val savedResultName: String) : FiveGpaUiEvents

    data class DeleteResult(var fiveSgpaResultName: FiveSgpaResultEntity) : FiveGpaUiEvents

    data class DeleteFiveGpaResultByReference(var fiveSgpaResultName: String) : FiveGpaUiEvents
    data class onCheckChanged(
        var info: SgpaResultDisplayFormatForFiveCgpaCalculation,
        var isChecked: Boolean,
        var index: Int,
        var sgpaNeeded: String,
        var resultNameRef: String,
    ) :
        FiveGpaUiEvents


    object showFiveCgpaSaveResultDB : FiveGpaUiEvents
    object hideFiveCgpaSaveResultDB : FiveGpaUiEvents
    object resetBackToDefaultValueFromErrorSRAFiveCgpa : FiveGpaUiEvents
    object saveFiveCgpaResult : FiveGpaUiEvents

    object helpFiveCgpa : FiveGpaUiEvents
    object loadFiveCgpaResult : FiveGpaUiEvents


    data class setFiveCgpaSRA(var saveResultAs: String) : FiveGpaUiEvents
    data class DeleteFiveCgpaResultByReference(var fiveCgpaResultName: String) : FiveGpaUiEvents


}