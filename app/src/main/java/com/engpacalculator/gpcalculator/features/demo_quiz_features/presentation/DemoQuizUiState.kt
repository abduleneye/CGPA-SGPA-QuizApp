package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.Results

data class DemoQuizUiState(
    val screenStatus: String = "",
    val questions: MutableList<Results> = mutableListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val demoQuizQuestionCategory: String = "",
    val quizIntroDialogBoxVisibility: Boolean = false,
    val questionCategory: String = "",
    val amountOfQuestions: String = "",
    val testLoad: Boolean = true,
    var questionIndex: Int = 0,
    var optionsList: MutableList<String> = mutableListOf(),
    var selectedOption: String = "",
    var isRadiobuttonEnabled: Boolean = true,
    var isNextButtonEnabled: Boolean = false,
    var currentScore: Int = 0,

    )
