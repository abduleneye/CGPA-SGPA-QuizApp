package com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository

import com.engpacalculator.gpcalculator.core.util.Resource
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.QuestionDetails
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(category: String, amount: String): //QuestionDetails
            Flow<Resource<QuestionDetails>>
}