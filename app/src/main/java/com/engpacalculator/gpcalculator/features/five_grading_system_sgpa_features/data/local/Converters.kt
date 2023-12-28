package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local

import GpCalculatorPrototype.Data.GpData
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromResultJson(json: String): ArrayList<GpData> {
        return jsonParser.fromJson<ArrayList<GpData>>(
            json,
            object : TypeToken<ArrayList<GpData>>() {}.type
        ) ?: ArrayList<GpData>()
    }

    @TypeConverter
    fun toResultJson(results: ArrayList<GpData>): String {
        return jsonParser.toJson(
            results,
            object : TypeToken<ArrayList<GpData>>() {}.type
        ) ?: "[]"
    }
}