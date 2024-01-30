package com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote

import com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.dto.QuestionsDetailsDto
import retrofit2.http.GET

interface QuestionApi {
    @GET("/api.php?amount=100&category=17&difficulty=easy&type=multiple")
    suspend fun getQuestions(): QuestionsDetailsDto

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}