package com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository

import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.QuestionDetails

interface QuestionRepository {
    suspend fun getQuestions(): QuestionDetails
}