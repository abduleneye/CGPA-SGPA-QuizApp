package com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.dto

import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.QuestionDetails

data class QuestionsDetailsDto(
    val response_code: Int,
    val results: List<ResultDto>
) {
    fun toQuestionDetails(): QuestionDetails {
        return QuestionDetails(
            results = results.map { it.toResults() }
        )
    }
}