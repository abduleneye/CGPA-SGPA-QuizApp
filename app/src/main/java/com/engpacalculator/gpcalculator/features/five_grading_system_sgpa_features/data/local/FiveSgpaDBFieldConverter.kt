package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local

import GpCalculatorPrototype.Data.GpData
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.five_sgpa_util.FiveSgpaJsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class FiveSgpaDBFieldConverter(
    private val fiveSgpaJsonParser: FiveSgpaJsonParser
) {

    @TypeConverter
    fun fromResultJson(json: String): ArrayList<GpData> {
        return fiveSgpaJsonParser.fromJson<ArrayList<GpData>>(
            json,
            object : TypeToken<ArrayList<GpData>>() {}.type
        ) ?: ArrayList<GpData>()
    }

    @TypeConverter
    fun toResultJson(results: ArrayList<GpData>): String {
        return fiveSgpaJsonParser.toJson(
            results,
            object : TypeToken<ArrayList<GpData>>() {}.type
        ) ?: "[]"
    }
}