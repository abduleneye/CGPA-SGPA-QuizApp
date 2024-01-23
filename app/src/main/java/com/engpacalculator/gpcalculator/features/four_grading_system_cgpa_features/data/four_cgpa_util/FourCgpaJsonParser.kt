package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.four_cgpa_util

import java.lang.reflect.Type

interface FourCgpaJsonParser {

    //get object from json string
    //generic function

    fun <T> fromFourCgpaResultJson(json: String, type: Type): T?

    //convert something to a json string
    fun <T> toFourCgpaResultJson(obj: T, type: Type): String?

}
