package com.example.gpcalculator.presentation.course_list_screen_components

sealed interface DialogBoxUiEvents {
    object showUnitMenuDropDown : DialogBoxUiEvents
    object hideUnitMenuDropDown : DialogBoxUiEvents
    object showGradeMenuDropDown : DialogBoxUiEvents
    object hideGradeMenuDropDown : DialogBoxUiEvents

    object resetResultField : DialogBoxUiEvents

    object showCourseDataEntriesContextmenu : DialogBoxUiEvents

    object hideCourseDataEntriesContextmenu : DialogBoxUiEvents

    data class setSelectedCourseGrade(var grade: String) : DialogBoxUiEvents
    data class setSelectedCourseUnit(var unit: String) : DialogBoxUiEvents
    data class setCourseCode(var courseCode: String) : DialogBoxUiEvents

    data class setTotalCourses(var totalCourses: String) : DialogBoxUiEvents

    data class setTotalCreditLoad(var totalCreditLoad: String) : DialogBoxUiEvents

    data class deleteCourseEntry(var itemToRemove: Int) : DialogBoxUiEvents

    data class editItemsEntries(
        var courseCodeEdit: String,
        var gradeEdit: String,
        var unitEdit: String
    ) : DialogBoxUiEvents

    data class updateCourseIndexEntry(var entryIndex: String) : DialogBoxUiEvents

    object showDataEntryDBox : DialogBoxUiEvents
    object hideDataEntryDBox : DialogBoxUiEvents
    object showBaseEntryDBox : DialogBoxUiEvents
    object hideBaseEntryDBox : DialogBoxUiEvents
    object showResultDBox : DialogBoxUiEvents
    object hideResultDBox : DialogBoxUiEvents
    object addEntriesToArrayList : DialogBoxUiEvents

    object replaceEditedInEntriesToArrayList : DialogBoxUiEvents


    object executeCalculation : DialogBoxUiEvents

    object resetTotalEntries : DialogBoxUiEvents

    object showCourseEntryEditDBox : DialogBoxUiEvents
    object hideCourseEntryEditDBox : DialogBoxUiEvents
    object resetAlreadyInList : DialogBoxUiEvents
    object resetDefaultValuesFromErrorsTNOC : DialogBoxUiEvents
    object resetDefaultLabelTextTNOC : DialogBoxUiEvents
    object resetDefaultValuesFromErrorsTNOCL : DialogBoxUiEvents
    object resetDefaultLabelTextTNOCL : DialogBoxUiEvents
    object showEditBaseEntryDBox : DialogBoxUiEvents
    object hideEditBaseEntryDBox : DialogBoxUiEvents

    data class dropDownAssumeText(val text: String) : DialogBoxUiEvents


}