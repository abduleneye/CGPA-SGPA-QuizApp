package com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model

data class Results(
    val correct_answer: String,
    // val difficulty: String,
    //val incorrect_answers: List<String>,
    val question: String,
    //val type: String,
    val answers: List<String>
)
