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
        //LoadQuestions("17", "3")
    }

    fun onEvent(event: DemoQuizUiEventClass) {
        when (event) {
            is DemoQuizUiEventClass.loadData -> {
                LoadQuestions(event.category, event.amount)


            }

            is DemoQuizUiEventClass.setQuizQuestionCategoryOnNavigate -> {

                _demoQuizUiState.update {
                    it.copy(
                        demoQuizQuestionCategory = event.category
                    )
                }

            }

            is DemoQuizUiEventClass.hideIntroDialogBoxVisibilty -> {
                _demoQuizUiState.update {
                    it.copy(
                        quizIntroDialogBoxVisibility = false
                    )
                }
            }

            is DemoQuizUiEventClass.showIntroDialogBoxVisibilty -> {
                _demoQuizUiState.update {
                    it.copy(
                        quizIntroDialogBoxVisibility = true
                    )
                }
            }

            is DemoQuizUiEventClass.setQuestionDetailsForReload -> {
                _demoQuizUiState.update {
                    it.copy(
                        questionCategory = event.category,
                        amountOfQuestions = event.amount
                    )
                }
            }

            is DemoQuizUiEventClass.incrementQuestionIndex -> {
//                val reShuffledOptions =
//                    _demoQuizUiState.value.questions.get(_demoQuizUiState.value.questionIndex).answers.shuffled()
//                        .toMutableList()
                val iterator = _demoQuizUiState.value.questions
                if (_demoQuizUiState.value.questionIndex < iterator.size - 1) {
                    _demoQuizUiState.update {
                        it.copy(
                            questionIndex = _demoQuizUiState.value.questionIndex + 1
                        )

                    }
                    _demoQuizUiState.update {
                        it.copy(
                            optionsList =
                            _demoQuizUiState.value.questions.get(_demoQuizUiState.value.questionIndex).answers.shuffled()
                                .toMutableList()
                        )

                    }

                }
            }

            is DemoQuizUiEventClass.enableRadioButton -> {
                _demoQuizUiState.update {
                    it.copy(
                        isRadiobuttonEnabled = true
                    )
                }
            }

            is DemoQuizUiEventClass.disableRadioButton -> {
                _demoQuizUiState.update {
                    it.copy(
                        isRadiobuttonEnabled = false
                    )
                }
            }

            is DemoQuizUiEventClass.enableNextButton -> {
                _demoQuizUiState.update {
                    it.copy(
                        isNextButtonEnabled = true
                    )
                }
            }

            is DemoQuizUiEventClass.disableNextButton -> {
                _demoQuizUiState.update {
                    it.copy(
                        isNextButtonEnabled = false
                    )
                }
            }

            is DemoQuizUiEventClass.setSelectedOption -> {
                _demoQuizUiState.update {
                    it.copy(
                        selectedOption = event.selectedOption
                    )
                }
                Log.d("CURRENT_OPTION", _demoQuizUiState.value.selectedOption)
            }

            is DemoQuizUiEventClass.currentScore -> {
                _demoQuizUiState.update {
                    it.copy(
                        currentScore = _demoQuizUiState.value.currentScore + 1
                    )
                }
            }

//            is DemoQuizUiEventClass.resetQuestionIndex -> {
//                _demoQuizUiState.update {
//                    it.copy(
//                        questionIndex = 0
//                    )
//                }
//            }

            else -> {}
        }
    }


    private fun LoadQuestions(category: String, amount: String) {
        _demoQuizUiState.value.questions.clear()
        _demoQuizUiState.update {
            it.copy(
                isLoading = false,
                questionIndex = 0,
                isRadiobuttonEnabled = true,
                isNextButtonEnabled = false,
                currentScore = 0
            )
        }
        viewModelScope.launch {
            // val questions = myDemoQuizRepository.getQuestions()
            myDemoQuizRepository.getQuestions(
                // _demoQuizUiState.value.demoQuizQuestionCategory
                category = category,
                amount = amount
            )
                .onEach { result ->
                    val loadingStatus: String = "LoadingStatus"
                    when (result) {
                        is Resource.Success -> {
                            _demoQuizUiState.value = demoQuizUiState.value.copy(
                                questions = result.data!!.results.toMutableList(),
                                optionsList = result.data.results.get(_demoQuizUiState.value.questionIndex).answers.shuffled()
                                    .toMutableList(),
                                isLoading = true,
                            )
                            Log.d(loadingStatus, "LoadedSuccessfully")
                            _demoQuizUiState.update {
                                it.copy(
                                    screenStatus = "Loaded Successfully"
                                )
                            }
                        }

                        is Resource.Loading -> {

//                            _demoQuizUiState.value = demoQuizUiState.value.copy(
//                                // questions = result.data!!.results,
//                                isLoading = false
//                            )
                            Log.d(loadingStatus, "IsLoading")
                            _demoQuizUiState.update {
                                it.copy(
                                    screenStatus = "Is Loading"
                                )
                            }


                        }

                        is Resource.Error -> {
                            Log.d(loadingStatus, "An Error occurred")
                            _demoQuizUiState.update {
                                it.copy(
                                    screenStatus = "An Error occurred"
                                )
                            }


//                            _demoQuizUiState.value = demoQuizUiState.value.copy(
//                                //questions = result.data!!.results,
//                                isLoading = false
//                            )
                        }

                        else -> {

                        }
                    }

                }.launchIn(this)

            Log.d("MyQuestions", "${questions}")

        }


    }
}


