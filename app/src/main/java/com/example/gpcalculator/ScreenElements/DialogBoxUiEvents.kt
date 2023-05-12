package com.example.gpcalculator.ScreenElements

sealed interface DialogBoxUiEvents{
    object showUnitMenuDropDown: DialogBoxUiEvents
    object hideUnitMenuDropDown: DialogBoxUiEvents
    object showGradeMenuDropDown: DialogBoxUiEvents
    object hideGradeMenuDropDown: DialogBoxUiEvents
    data class setSelectedCourseGrade(var grade: String):  DialogBoxUiEvents
    data class setSelectedCourseUnit(var unit: String): DialogBoxUiEvents
    data class setCourseCode(var courseCode: String): DialogBoxUiEvents
    object showDBox: DialogBoxUiEvents
    object hideDBox: DialogBoxUiEvents
    object addEntriesToArrayList: DialogBoxUiEvents

}