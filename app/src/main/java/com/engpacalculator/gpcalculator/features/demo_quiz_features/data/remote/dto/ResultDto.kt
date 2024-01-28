package com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.dto

import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.Results

data class ResultDto(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
) {
    fun toResults(): Results {
        return Results(
            correct_answer = correct_answer,
            question = question,
            // incorrect_answers = incorrect_answers,
            answers = incorrect_answers + correct_answer
        )
    }
}