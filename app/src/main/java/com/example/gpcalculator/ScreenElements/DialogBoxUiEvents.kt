package com.example.gpcalculator.ScreenElements

sealed interface DialogBoxUiEvents{
    object showUnitMenuDropDown: DialogBoxUiEvents
    object hideUnitMenuDropDown: DialogBoxUiEvents
    object showGradeMenuDropDown: DialogBoxUiEvents
    object hideGradeMenuDropDown: DialogBoxUiEvents

    object resetResultField: DialogBoxUiEvents
    data class setSelectedCourseGrade(var grade: String):  DialogBoxUiEvents
    data class setSelectedCourseUnit(var unit: String): DialogBoxUiEvents
    data class setCourseCode(var courseCode: String): DialogBoxUiEvents

    data class setTotalCourses(var totalCourses: String): DialogBoxUiEvents

    data class setTotalCreditLoad(var totalCreditLoad: String): DialogBoxUiEvents
    object showDataEntryDBox: DialogBoxUiEvents
    object hideDataEntryDBox: DialogBoxUiEvents
    object showBaseEntryDBox: DialogBoxUiEvents
    object hideBaseEntryDBox: DialogBoxUiEvents
    object showResultDBox: DialogBoxUiEvents
    object hideResultDBox: DialogBoxUiEvents
    object addEntriesToArrayList: DialogBoxUiEvents
    object executeCalculation: DialogBoxUiEvents

}