package com.example.gpcalculator.ScreenElements

data class DialogBoxState (

    val courseCode: String = "",
    val selectedCourseUnit: String = "",
    val selectedCourseGrade: String = "",
    val isUnitDropDownMenuExpanded: Boolean = false,
    val isGradeDropDownMenuExpanded: Boolean = false,
    var dialogBoxVisibility:  Boolean  = false


)