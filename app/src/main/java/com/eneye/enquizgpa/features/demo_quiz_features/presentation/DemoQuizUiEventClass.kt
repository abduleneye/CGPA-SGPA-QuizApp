package com.eneye.enquizgpa.features.demo_quiz_features.presentation

sealed interface DemoQuizUiEventClass {

    data class loadData(var category: String, var amount: String) : DemoQuizUiEventClass
    //data class setQuizQuestionCategoryOnNavigate(var category: String) : DemoQuizUiEventClass

    object showIntroDialogBoxVisibilty : DemoQuizUiEventClass
    object hideIntroDialogBoxVisibilty : DemoQuizUiEventClass

    object incrementQuestionIndex : DemoQuizUiEventClass
    object currentScore : DemoQuizUiEventClass

    object enableRadioButton : DemoQuizUiEventClass
    object disableRadioButton : DemoQuizUiEventClass

    object enableNextButton : DemoQuizUiEventClass
    object disableNextButton : DemoQuizUiEventClass

    data class setSelectedOption(var selectedOption: String) : DemoQuizUiEventClass
    data class setQuestionDetailsForReload(var category: String, var amount: String) :
        DemoQuizUiEventClass

    object showCorrectnessDiaogBox : DemoQuizUiEventClass
    object hideCorrectnessDialogBox : DemoQuizUiEventClass
    object showEndOfQuestionsDialogBox : DemoQuizUiEventClass
    object hideEndOfQuestionsDialogBox : DemoQuizUiEventClass

    data class setQuestionAnsweredStatus(var status: String) : DemoQuizUiEventClass


}