package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.five_cgpa_util

import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.four_cgpa_util.FourCgpaJsonParser
import com.google.gson.Gson
import java.lang.reflect.Type

class FourCgpaGsonParserFourCgpa(
    private val gson: Gson
) : FourCgpaJsonParser {
    override fun <T> fromFourCgpaResultJson(json: String, type: Type): T? {

        return gson.fromJson(json, type)
    }

    override fun <T> toFourCgpaResultJson(obj: T, type: Type): String? {

        return gson.toJson(obj, type)
    }

}