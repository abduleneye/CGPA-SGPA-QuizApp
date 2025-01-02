package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local

import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.four_cgpa_util.FourCgpaJsonParser
import com.google.gson.reflect.TypeToken


class FourCgpaDBFieldConverter(
    private val fourCgpaJsonParser: FourCgpaJsonParser,
) {


    fun fromFourCgpaResultJson(json: String): ArrayList<String> {
        return fourCgpaJsonParser.fromFourCgpaResultJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: ArrayList<String>()
    }

    fun toFourCgpaResultJson(results: ArrayList<String>): String {
        return fourCgpaJsonParser.toFourCgpaResultJson(
            results,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }
}