package Quiz.Data.Presentation.Domain

fun main() {

    var physics: Questions = Questions()

    var questions: ArrayList<QuizFormat> = ArrayList()

    questions.add(
        QuizFormat(
            question = "What is a verb?",
            optionA = "An acion word",
            optionB = "A doing word",
            optionC = " a speech",
            optionD = "I dont know",
            correctAnswer = "An Action Word"
        )
    )

    questions.add(
        QuizFormat(
            question = "What is a verb?",
            optionA = "An acion word",
            optionB = "A doing word",
            optionC = " a speech",
            optionD = "I dont know",
            correctAnswer = "An Action Word"
        )
    )

    questions.add(
        QuizFormat(
            question = "What is a verb?",
            optionA = "An acion word",
            optionB = "A doing word",
            optionC = " a speech",
            optionD = "I dont know",
            correctAnswer = "An Action Word"
        )
    )
//    for(i in 0.. questions.size){
//
//        println(questions)
//    }

    println("Fromclass ${physics.physicsQuestions()}")

}