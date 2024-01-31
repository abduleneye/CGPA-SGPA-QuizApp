package com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote

import com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.dto.QuestionsDetailsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {
    @GET("/api.php?amount=10&category=category&difficulty=easy&type=multiple")
    suspend fun getQuestions(@Query("category") category: String): QuestionsDetailsDto

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}