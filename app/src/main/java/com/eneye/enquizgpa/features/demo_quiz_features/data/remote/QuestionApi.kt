package com.eneye.enquizgpa.features.demo_quiz_features.data.remote

import com.eneye.enquizgpa.features.demo_quiz_features.data.remote.dto.QuestionsDetailsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {
    @GET("/api.php?amount=amount&category=category&difficulty=easy&type=multiple")
    suspend fun getQuestions(
        @Query("category") category: String,
        @Query("amount") amount: String,
    ): QuestionsDetailsDto

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}