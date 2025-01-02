package com.eneye.enquizgpa.features.demo_quiz_features.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Results(
    val correct_answer: String,
    // val difficulty: String,
    //val incorrect_answers: List<String>,
    val question: String,
    //val type: String,
    val answers: List<String>,
) : Parcelable
