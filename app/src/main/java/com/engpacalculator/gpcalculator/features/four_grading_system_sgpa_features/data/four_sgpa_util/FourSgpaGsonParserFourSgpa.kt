package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.five_sgpa_util

import com.google.gson.Gson
import java.lang.reflect.Type

class FourSgpaGsonParserFourSgpa(
    private val gson: Gson
) : FourSgpaJsonParser {
    override fun <T> fromFourSgpaResultJson(json: String, type: Type): T? {

        return gson.fromJson(json, type)
    }

    override fun <T> toFourSgpaResultJson(obj: T, type: Type): String? {

        return gson.toJson(obj, type)
    }

}