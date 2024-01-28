package Quiz.Data.Presentation.Domain

class Questions {

    fun physicsQuestions() {

        var questions: ArrayList<QuizFormat> = ArrayList()
        var remark = ""
        var score = 0


        questions.add(
            QuizFormat(
                question = "how many planets do we have?",
                optionA = "7",
                optionB = "3",
                optionC = "6",
                optionD = "9",
                correctAnswer = "9"
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

        for (i in 0..questions.size - 1) {


            val resultOptionsMap = mapOf<String, String>(
                "A" to questions[i].optionA,
                "B" to questions[i].optionB,
                "C" to questions[i].optionC,
                "D" to questions[i].optionD,
            )
            println("Select an answer..")
            println(questions[i])
            var ans = readlnOrNull()
            if (ans?.uppercase() == questions[i].correctAnswer.uppercase()) {
                score++
                remark = "correct"
                println("${remark}")

            } else {
                remark = "wrong"
                println("${remark}, the correct answer is: ${questions[i].correctAnswer.uppercase()}")

            }


//            if(ans?.uppercase() == resultOptionsMap.get("a")) {
//                score++
//
//            }
        }

        println("You Scored ${score} out of ${questions.size}")
    }


}