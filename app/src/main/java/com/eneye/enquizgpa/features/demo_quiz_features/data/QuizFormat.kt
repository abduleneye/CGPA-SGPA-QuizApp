package Quiz.Data.Presentation.Domain

data class QuizFormat(
    var question: String,
    var optionA: String,
    var optionB: String,
    var optionC: String,
    var optionD: String,
    var correctAnswer: String
)
