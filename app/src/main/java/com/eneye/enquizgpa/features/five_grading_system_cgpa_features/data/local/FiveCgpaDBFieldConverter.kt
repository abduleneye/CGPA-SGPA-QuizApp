package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.five_cgpa_util.FiveCgpaJsonParser
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class FiveCgpaDBFieldConverter(
    private val fiveCgpaJsonParser: FiveCgpaJsonParser,
) {

    @TypeConverter
    fun fromFiveCgpaResultJson(json: String): ArrayList<String> {
        return fiveCgpaJsonParser.fromFiveCgpaResultJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: ArrayList<String>()
    }

    @TypeConverter
    fun toFiveCgpaResultJson(results: ArrayList<String>): String {
        return fiveCgpaJsonParser.toFiveCgpaResultJson(
            results,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }
}