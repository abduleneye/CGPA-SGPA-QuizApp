package com.eneye.enquizgpa.features.demo_quiz_features.domain.repository

import com.eneye.enquizgpa.core.util.Resource
import com.eneye.enquizgpa.features.demo_quiz_features.domain.model.QuestionDetails
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(category: String, amount: String): //QuestionDetails
            Flow<Resource<QuestionDetails>>
}