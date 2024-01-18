package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation

import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FiveSgpaResultEntity

sealed interface FiveSgpaUiEvents {
    object showUnitMenuDropDown : FiveSgpaUiEvents
    object hideUnitMenuDropDown : FiveSgpaUiEvents
    object showGradeMenuDropDown : FiveSgpaUiEvents
    object hideGradeMenuDropDown : FiveSgpaUiEvents

    object resetResultField : FiveSgpaUiEvents

    object showCourseDataEntriesContextmenu : FiveSgpaUiEvents

    object hideCourseDataEntriesContextmenu : FiveSgpaUiEvents

    data class setSelectedCourseGrade(var grade: String) : FiveSgpaUiEvents
    data class setSelectedCourseUnit(var unit: String) : FiveSgpaUiEvents
    data class setCourseCode(var courseCode: String) : FiveSgpaUiEvents

    data class setTotalCourses(var totalCourses: String) : FiveSgpaUiEvents

    data class setTotalCreditLoad(var totalCreditLoad: String) : FiveSgpaUiEvents

    data class deleteCourseEntry(var itemToRemove: Int) : FiveSgpaUiEvents

    data class editItemsEntries(
        var courseCodeEdit: String,
        var gradeEdit: String,
        var unitEdit: String
    ) : FiveSgpaUiEvents

    data class updateCourseIndexEntry(var entryIndex: String) : FiveSgpaUiEvents

    object showDataEntryDBox : FiveSgpaUiEvents
    object hideDataEntryDBox : FiveSgpaUiEvents
    object showBaseEntryDBox : FiveSgpaUiEvents
    object showResultDBox : FiveSgpaUiEvents
    object addEntriesToArrayList : FiveSgpaUiEvents

    object replaceEditedInEntriesToArrayList : FiveSgpaUiEvents


    object executeCalculation : FiveSgpaUiEvents

    object resetTotalEntries : FiveSgpaUiEvents

    object showCourseEntryEditDBox : FiveSgpaUiEvents
    object hideCourseEntryEditDBox : FiveSgpaUiEvents
    object resetAlreadyInList : FiveSgpaUiEvents
    object resetBackToDefaultValuesFromErrorsTNOC : FiveSgpaUiEvents
    object resetDefaultValuesFromErrorsTNOCL : FiveSgpaUiEvents
    object resetDefaultLabelTextTNOCL : FiveSgpaUiEvents
    object showEditBaseEntryDBox : FiveSgpaUiEvents
    object hideEditBaseEntryRegardlessDBox : FiveSgpaUiEvents
    object hideBaseEntryRegardlessDBox : FiveSgpaUiEvents
    object hideBaseEntryDBox : FiveSgpaUiEvents
    object hideEditBaseEntryDBox : FiveSgpaUiEvents
    object hideClearConfirmationDBox : FiveSgpaUiEvents
    object showClearConfirmationDBox : FiveSgpaUiEvents
    object showSaveResultDBox : FiveSgpaUiEvents
    object hideSaveResultDBox : FiveSgpaUiEvents


    data class dropDownAssumeText(val text: String) : FiveSgpaUiEvents
    data class setPrevTotalCourses(val text: String) : FiveSgpaUiEvents

    object hideHomeAdShimmerEffect : FiveSgpaUiEvents
    object showHomeAdShimmerEffect : FiveSgpaUiEvents
    object hideAboutAdShimmerEffect : FiveSgpaUiEvents
    object showAboutAdShimmerEffect : FiveSgpaUiEvents
    object resetBackToDefaultValuesFromErrorsCC : FiveSgpaUiEvents
    object resetBackToDefaultValuesFromErrorsCU : FiveSgpaUiEvents
    object resetBackToDefaultValuesFromErrorsCG : FiveSgpaUiEvents
    object resetBackToDefaultValuesFromErrorsECC : FiveSgpaUiEvents
    object resetBackToDefaultValueFromErrorSRA : FiveSgpaUiEvents
    object saveFiveSgpaResult : FiveSgpaUiEvents
    object loadResult : FiveSgpaUiEvents
    object hideSaveResultsFiveSgpa : FiveSgpaUiEvents
    object executeCgpaCalculation : FiveSgpaUiEvents

    data class setTotalNumberOfEditedCourses(val noOfEditedTotalCourse: String) :
        FiveSgpaUiEvents

    data class setSRA(val savedResultName: String) : FiveSgpaUiEvents

    data class DeleteResult(var result: FiveSgpaResultEntity) : FiveSgpaUiEvents

    data class DeleteResultByReference(var result: String) : FiveSgpaUiEvents
    data class onCheckChanged(
        var info: SgpaResultDisplayFormatForFiveCgpaCalculation,
        var isChecked: Boolean,
        var index: Int,
        var sgpaNeeded: String,
        var resultNameRef: String,
    ) :
        FiveSgpaUiEvents


    object showFiveCgpaSaveResultDB : FiveSgpaUiEvents
    object hideFiveCgpaSaveResultDB : FiveSgpaUiEvents
    object resetBackToDefaultValueFromErrorSRAFiveCgpa : FiveSgpaUiEvents
    object saveFiveCgpaResult : FiveSgpaUiEvents

    object helpFiveCgpa : FiveSgpaUiEvents
    object loadFiveCgpaResult : FiveSgpaUiEvents


    data class setFiveCgpaSRA(var saveResultAs: String) : FiveSgpaUiEvents

}