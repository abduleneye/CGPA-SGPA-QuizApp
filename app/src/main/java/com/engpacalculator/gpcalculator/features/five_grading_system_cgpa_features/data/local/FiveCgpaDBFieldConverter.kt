package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.five_cgpa_util.FiveCgpaJsonParser
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class FiveCgpaDBFieldConverter(
    private val fiveCgpaJsonParser: FiveCgpaJsonParser
) {

    @TypeConverter
    fun fromResultJson(json: String): ArrayList<String> {
        return fiveCgpaJsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: ArrayList<String>()
    }

    @TypeConverter
    fun toResultJson(results: ArrayList<String>): String {
        return fiveCgpaJsonParser.toJson(
            results,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }
}