package com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation

import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFourCgpaCalculation

sealed interface FourGpaUiEvents {
    object showUnitMenuDropDown : FourGpaUiEvents
    object hideUnitMenuDropDown : FourGpaUiEvents
    object showGradeMenuDropDown : FourGpaUiEvents
    object hideGradeMenuDropDown : FourGpaUiEvents

    object resetResultField : FourGpaUiEvents

    object showCourseDataEntriesContextmenu : FourGpaUiEvents

    object hideCourseDataEntriesContextmenu : FourGpaUiEvents

    data class setSelectedCourseGrade(var grade: String) : FourGpaUiEvents
    data class setSelectedCourseUnit(var unit: String) : FourGpaUiEvents
    data class setCourseCode(var courseCode: String) : FourGpaUiEvents

    data class setTotalCourses(var totalCourses: String) : FourGpaUiEvents

    data class setTotalCreditLoad(var totalCreditLoad: String) : FourGpaUiEvents

    data class deleteCourseEntry(var itemToRemove: Int) : FourGpaUiEvents

    data class editItemsEntries(
        var courseCodeEdit: String,
        var gradeEdit: String,
        var unitEdit: String
    ) : FourGpaUiEvents

    data class updateCourseIndexEntry(var entryIndex: String) : FourGpaUiEvents

    object showDataEntryDBox : FourGpaUiEvents
    object hideDataEntryDBox : FourGpaUiEvents
    object showBaseEntryDBox : FourGpaUiEvents
    object showResultDBox : FourGpaUiEvents
    object addEntriesToArrayList : FourGpaUiEvents

    object replaceEditedInEntriesToArrayList : FourGpaUiEvents


    object executeCalculation : FourGpaUiEvents

    object resetTotalEntries : FourGpaUiEvents

    object showCourseEntryEditDBox : FourGpaUiEvents
    object hideCourseEntryEditDBox : FourGpaUiEvents
    object resetAlreadyInList : FourGpaUiEvents
    object resetBackToDefaultValuesFromErrorsTNOC : FourGpaUiEvents
    object resetDefaultValuesFromErrorsTNOCL : FourGpaUiEvents
    object resetDefaultLabelTextTNOCL : FourGpaUiEvents
    object showEditBaseEntryDBox : FourGpaUiEvents
    object hideEditBaseEntryRegardlessDBox : FourGpaUiEvents
    object hideBaseEntryRegardlessDBox : FourGpaUiEvents
    object hideBaseEntryDBox : FourGpaUiEvents
    object hideEditBaseEntryDBox : FourGpaUiEvents
    object hideClearConfirmationDBox : FourGpaUiEvents
    object showClearConfirmationDBox : FourGpaUiEvents
    object showSaveResultDBox : FourGpaUiEvents
    object hideFourSgpaSaveResultDB : FourGpaUiEvents


    data class dropDownAssumeText(val text: String) : FourGpaUiEvents
    data class setPrevTotalCourses(val text: String) : FourGpaUiEvents

    object hideHomeAdShimmerEffect : FourGpaUiEvents
    object showHomeAdShimmerEffect : FourGpaUiEvents
    object hideAboutAdShimmerEffect : FourGpaUiEvents
    object showAboutAdShimmerEffect : FourGpaUiEvents
    object resetBackToDefaultValuesFromErrorsCC : FourGpaUiEvents
    object resetBackToDefaultValuesFromErrorsCU : FourGpaUiEvents
    object resetBackToDefaultValuesFromErrorsCG : FourGpaUiEvents
    object resetBackToDefaultValuesFromErrorsECC : FourGpaUiEvents
    object resetBackToDefaultValueFromErrorSRA : FourGpaUiEvents
    object saveFourSgpaResult : FourGpaUiEvents
    object loadResult : FourGpaUiEvents
    object hideSaveResultsFourGpa : FourGpaUiEvents
    object executeCgpaCalculation : FourGpaUiEvents

    data class setTotalNumberOfEditedCourses(val noOfEditedTotalCourse: String) :
        FourGpaUiEvents

    data class setSRA(val savedResultName: String) : FourGpaUiEvents

    data class DeleteResult(var fourSgpaResultName: FourSgpaResultEntity) : FourGpaUiEvents

    data class DeleteFourGpaResultByReference(var fourSgpaResultName: String) : FourGpaUiEvents
    data class onCheckChanged(
        var info: SgpaResultDisplayFormatForFourCgpaCalculation,
        var isChecked: Boolean,
        var index: Int,
        var sgpaNeeded: String,
        var resultNameRef: String,
    ) :
        FourGpaUiEvents


    object showFourCgpaSaveResultDB : FourGpaUiEvents
    object hideFourCgpaSaveResultDB : FourGpaUiEvents
    object resetBackToDefaultValueFromErrorSRAFourCgpa : FourGpaUiEvents
    object saveFourCgpaResult : FourGpaUiEvents

    object helpFourCgpa : FourGpaUiEvents
    object loadFourCgpaResult : FourGpaUiEvents
    object hideFourCgpaIntroDialogBox : FourGpaUiEvents
    object showFourCgpaIntroDialogBox : FourGpaUiEvents


    data class setFourCgpaSRA(var saveResultAs: String) : FourGpaUiEvents
    data class DeleteFourCgpaResultByReference(var fourCgpaResultName: String) : FourGpaUiEvents


}