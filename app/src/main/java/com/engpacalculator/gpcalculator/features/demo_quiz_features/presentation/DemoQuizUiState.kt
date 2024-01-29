package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.Results

data class DemoQuizUiState(
    val questions: List<Results> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
