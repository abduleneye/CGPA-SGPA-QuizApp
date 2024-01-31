package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

sealed interface DemoQuizUiEventClass {

    data class loadData(var category: String) : DemoQuizUiEventClass
    data class setQuizQuestionCategoryOnNavigate(var category: String) : DemoQuizUiEventClass
}