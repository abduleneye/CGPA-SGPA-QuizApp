package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoQuizViewModel @Inject constructor(
    private val myDemoQuizRepository: QuestionRepository,


    ) : ViewModel() {

    private val _demoQuizUiState = MutableStateFlow(DemoQuizUiState())
    val demoQuizUiState = _demoQuizUiState.asStateFlow()

    init {

        LoadQuestions()
    }

    private fun LoadQuestions() {

        viewModelScope.launch {
            val questions = myDemoQuizRepository.getQuestions()
            _demoQuizUiState.update {
                it.copy(
                    questions = questions.results
                )
            }
            Log.d("MyQuestions", "${questions}")

        }


    }
}