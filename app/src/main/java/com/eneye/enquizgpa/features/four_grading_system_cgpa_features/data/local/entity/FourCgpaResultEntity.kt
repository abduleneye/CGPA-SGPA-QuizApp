package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class FourCgpaResultEntity(
    val resultEntries: ArrayList<String> = ArrayList(),
    val gp: String = "",
    val remark: String = "",
    val resultName: String = "",
    val resultGpaDescriptor: String = "",
    @PrimaryKey val id: Int? = null,
) : Parcelable
