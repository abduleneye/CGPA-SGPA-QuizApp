package com.engpacalculator.gpcalculator.features.demo_quiz_features.data

import android.os.Build
import androidx.annotation.RequiresExtension
import com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.QuestionApi
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.QuestionDetails
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository.QuestionRepository

class QuestionsRepositoryImplementation(
    private val api: QuestionApi
) : QuestionRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getQuestions(): QuestionDetails {

        val results = api.getQuestions()
        return results.toQuestionDetails()
    }

}