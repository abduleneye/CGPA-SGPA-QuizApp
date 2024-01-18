package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.five_sgpa_util

import com.google.gson.Gson
import java.lang.reflect.Type

class FiveSgpaGsonParserFiveSgpa(
    private val gson: Gson
) : FiveSgpaJsonParser {
    override fun <T> fromJson(json: String, type: Type): T? {

        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {

        return gson.toJson(obj, type)
    }

}