package com.eneye.enquizgpa.features.demo_quiz_features.data

data class Result(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String,
)

//https://opentdb.com/api.php?amount=10