package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engpacalculator.gpcalculator.core.util.Resource
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

        // LoadQuestions()
    }

    fun onEvent(event: DemoQuizUiEventClass) {
        when (event) {
            is DemoQuizUiEventClass.loadData -> {
                LoadQuestions(event.category)


            }

            is DemoQuizUiEventClass.setQuizQuestionCategoryOnNavigate -> {

                _demoQuizUiState.update {
                    it.copy(
                        demoQuizQuestionCategory = event.category
                    )
                }

            }
        }
    }


    private fun LoadQuestions(category: String) {

        viewModelScope.launch {
            // val questions = myDemoQuizRepository.getQuestions()
            myDemoQuizRepository.getQuestions(
                // _demoQuizUiState.value.demoQuizQuestionCategory
                category = category
            )
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _demoQuizUiState.value = demoQuizUiState.value.copy(
                                questions = result.data!!.results,
                                isLoading = true
                            )
                        }

                        is Resource.Loading -> {

                            _demoQuizUiState.value = demoQuizUiState.value.copy(
                                // questions = result.data!!.results,
                                isLoading = false
                            )

                        }

                        is Resource.Error -> {

                            _demoQuizUiState.value = demoQuizUiState.value.copy(
                                //questions = result.data!!.results,
                                isLoading = false
                            )
                        }
                    }

                }.launchIn(this)

            Log.d("MyQuestions", "${questions}")

        }


    }
}


