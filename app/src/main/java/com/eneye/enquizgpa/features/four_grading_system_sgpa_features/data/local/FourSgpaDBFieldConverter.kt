package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.local

import GpCalculatorPrototype.Data.FourGpData
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.five_sgpa_util.FourSgpaJsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class FourSgpaDBFieldConverter(
    private val FourSgpaJsonParser: FourSgpaJsonParser,
) {

    @TypeConverter
    fun fromFourSgpaResultJson(json: String): ArrayList<FourGpData> {
        return FourSgpaJsonParser.fromFourSgpaResultJson<ArrayList<FourGpData>>(
            json,
            object : TypeToken<ArrayList<FourGpData>>() {}.type
        ) ?: ArrayList<FourGpData>()
    }

    @TypeConverter
    fun toFourSgpaResultJson(results: ArrayList<FourGpData>): String {
        return FourSgpaJsonParser.toFourSgpaResultJson(
            results,
            object : TypeToken<ArrayList<FourGpData>>() {}.type
        ) ?: "[]"
    }
}