package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.five_cgpa_util

import com.google.gson.Gson
import java.lang.reflect.Type

class FiveCgpaGsonParserFiveCgpa(
    private val gson: Gson
) : FiveCgpaJsonParser {
    override fun <T> fromJson(json: String, type: Type): T? {

        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {

        return gson.toJson(obj, type)
    }

}