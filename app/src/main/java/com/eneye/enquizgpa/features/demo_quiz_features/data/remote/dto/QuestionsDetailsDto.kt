package com.eneye.enquizgpa.features.demo_quiz_features.data.remote.dto

import androidx.annotation.Keep
import com.eneye.enquizgpa.features.demo_quiz_features.domain.model.QuestionDetails

@Keep
data class QuestionsDetailsDto(
    val response_code: Int,
    val results: List<ResultDto>,
) {
    @Keep
    fun toQuestionDetails(): QuestionDetails {
        return QuestionDetails(
            results = results.map { it.toResults() }
        )
    }
}