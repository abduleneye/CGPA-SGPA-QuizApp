package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.five_sgpa_util

import java.lang.reflect.Type

interface FourSgpaJsonParser {

    //get object from json string
    //generic function

    fun <T> fromFourSgpaResultJson(json: String, type: Type): T?

    //convert something to a json string
    fun <T> toFourSgpaResultJson(obj: T, type: Type): String?

}
