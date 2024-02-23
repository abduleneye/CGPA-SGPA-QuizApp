package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import android.os.Parcelable
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.Results
import kotlinx.parcelize.RawValue

@kotlinx.parcelize.Parcelize
data class DemoQuizUiState(
    val screenStatus: String = "",
    val questions: @RawValue MutableList<Results> = mutableListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val demoQuizQuestionCategory: String = "",
    val quizIntroDialogBoxVisibility: Boolean = false,
    val questionCategory: String = "",
    val amountOfQuestions: String = "",
    val testLoad: Boolean = true,
    var questionIndex: Int = 0,
    var optionsList: @RawValue MutableList<String> = mutableListOf(),
    var selectedOption: String = "",
    var isRadiobuttonEnabled: Boolean = true,
    var isNextButtonEnabled: Boolean = false,
    var currentScore: Int = 0,
    var correctnessDialogBoxVisibility: Boolean = false,
    var endOfQuestionsDialogBoxVisibility: Boolean = false,
    var questionStatus: String = ""

) : Parcelable
