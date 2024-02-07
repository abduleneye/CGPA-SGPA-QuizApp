package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

sealed interface DemoQuizUiEventClass {

    data class loadData(var category: String, var amount: String) : DemoQuizUiEventClass
    data class setQuizQuestionCategoryOnNavigate(var category: String) : DemoQuizUiEventClass

    object showIntroDialogBoxVisibilty : DemoQuizUiEventClass
    object hideIntroDialogBoxVisibilty : DemoQuizUiEventClass

    object incrementQuestionIndex : DemoQuizUiEventClass

    // object resetQuestionIndex : DemoQuizUiEventClass

    data class setQuestionDetailsForReload(var category: String, var amount: String) :
        DemoQuizUiEventClass


}