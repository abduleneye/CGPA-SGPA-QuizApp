package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.util

import java.lang.reflect.Type

interface JsonParser {

    //get object from json string
    //generic function

    fun <T> fromJson(json: String, type: Type): T?

    //convert something to a json string
    fun <T> toJson(obj: T, type: Type): String?

}
