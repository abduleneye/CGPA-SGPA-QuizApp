package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.five_cgpa_util

import java.lang.reflect.Type

interface FiveCgpaJsonParser {

    //get object from json string
    //generic function

    fun <T> fromJson(json: String, type: Type): T?

    //convert something to a json string
    fun <T> toJson(obj: T, type: Type): String?

}
