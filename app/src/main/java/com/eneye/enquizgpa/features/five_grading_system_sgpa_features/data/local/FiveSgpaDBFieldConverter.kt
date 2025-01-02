package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local

import GpCalculatorPrototype.Data.FiveGpData
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.five_sgpa_util.FiveSgpaJsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class FiveSgpaDBFieldConverter(
    private val fiveSgpaJsonParser: FiveSgpaJsonParser,
) {

    @TypeConverter
    fun fromFiveSgpaResultJson(json: String): ArrayList<FiveGpData> {
        return fiveSgpaJsonParser.fromFiveSgpaResultJson<ArrayList<FiveGpData>>(
            json,
            object : TypeToken<ArrayList<FiveGpData>>() {}.type
        ) ?: ArrayList<FiveGpData>()
    }

    @TypeConverter
    fun toFiveSgpaResultJson(results: ArrayList<FiveGpData>): String {
        return fiveSgpaJsonParser.toFiveSgpaResultJson(
            results,
            object : TypeToken<ArrayList<FiveGpData>>() {}.type
        ) ?: "[]"
    }
}