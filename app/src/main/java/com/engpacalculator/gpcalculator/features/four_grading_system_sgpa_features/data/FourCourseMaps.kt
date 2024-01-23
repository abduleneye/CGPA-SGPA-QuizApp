package GpCalculatorPrototype.Data

data class FourCourseMaps(

    val sixUnit_GradeMap: Map<String, Int> = mapOf(
        "A" to 30,
        "B" to 24,
        "C" to 18,
        "D" to 12,
        "E" to 6,
        "F" to 0
    ),
    val fourUnit_GradeMap: Map<String, Int> = mapOf(
        "A" to 20,
        "B" to 16,
        "C" to 12,
        "D" to 10,
        "E" to 4,
        "F" to 0
    ),
    val threeUnit_GradeMap: Map<String, Int> = mapOf(
        "A" to 15,
        "B" to 12,
        "C" to 9,
        "D" to 6,
        "E" to 3,
        "F" to 0
    ),
    val twoUnit_GradeMap: Map<String, Int> = mapOf(
        "A" to 10,
        "B" to 8,
        "C" to 6,
        "D" to 4,
        "E" to 2,
        "F" to 0
    ),
    val oneUnit_GradeMap: Map<String, Int> = mapOf(
        "A" to 5,
        "B" to 4,
        "C" to 3,
        "D" to 2,
        "E" to 1,
        "F" to 0
    )

)