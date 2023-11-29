package GpCalculatorPrototype

import GpCalculatorPrototype.Data.CourseDataEntries
import GpCalculatorPrototype.Data.CourseMaps
import GpCalculatorPrototype.Data.CoursesUnitPointArrayList
import GpCalculatorPrototype.Data.GpData
import java.util.Locale

//import kotlin.math.round


val coursePointObj = CoursesUnitPointArrayList()
val courseMapObj = CourseMaps()
val coursesDataEntryObj = CourseDataEntries()
val totalCourses = 0


fun main() {

    println("Welcome to Gp Calculator>>>:")
    println("Please enter your total credit load for this semester:")
    var totalCreditLoad = readln().toInt()
    println("Please enter your total number of courses for this semester:")
    var totalNumberOfCourses = readln().toInt()


    enterYourCourses(totalNumberOfCourses)
    courseValueMapper(coursesDataEntryObj.coursesDataEntry)
    println(operations(totalCreditLoad))
    println(coursesDataEntryObj.coursesDataEntry)
    println("Testing master")
    println("Retesting_commit")


}


fun operations(totalCreditLoad: Int): String {

    coursesDataEntryObj.threeUnitCoursesPointSum =
        coursePointObj.threeUnitA.sum() + coursePointObj.threeUnitB.sum() + coursePointObj.threeUnitC.sum() + coursePointObj.threeUnitD.sum() + coursePointObj.threeUnitE.sum() + coursePointObj.threeUnitF.sum()
    coursesDataEntryObj.twoUnitCoursesPointSum =
        coursePointObj.twoUnitA.sum() + coursePointObj.twoUnitB.sum() + coursePointObj.twoUnitC.sum() + coursePointObj.twoUnitD.sum() + coursePointObj.twoUnitE.sum() + coursePointObj.twoUnitF.sum()
    coursesDataEntryObj.oneUnitCoursesPointSum =
        coursePointObj.oneUnitA.sum() + coursePointObj.oneUnitB.sum() + coursePointObj.oneUnitC.sum() + coursePointObj.oneUnitD.sum() + coursePointObj.oneUnitE.sum() + coursePointObj.oneUnitF.sum()

    coursesDataEntryObj.totalCoursesPointSum =
        (coursesDataEntryObj.threeUnitCoursesPointSum + coursesDataEntryObj.twoUnitCoursesPointSum + coursesDataEntryObj.oneUnitCoursesPointSum).toDouble()

    return ("Your Gp is: ${coursesDataEntryObj.totalCoursesPointSum / totalCreditLoad}")
}

fun courseValueMapper(courseGrade: ArrayList<GpData>) {


    courseGrade.forEach { courseData ->

        when (courseData) {

            //For three unit Courses

            GpData(courseCode = courseData.courseCode, courseGrade = "A", courseUnit = 3) -> {

                courseMapObj.threeUnit_GradeMap["A"]?.let { coursePointObj.threeUnitA.add(it) }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "B", courseUnit = 3) -> {

                courseMapObj.threeUnit_GradeMap["B"]?.let { coursePointObj.threeUnitB.add(it) }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "C", courseUnit = 3) -> {

                courseMapObj.threeUnit_GradeMap["C"]?.let { coursePointObj.threeUnitC.add(it) }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "D", courseUnit = 3) -> {

                courseMapObj.threeUnit_GradeMap["D"]?.let { coursePointObj.threeUnitD.add(it) }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "E", courseUnit = 3) -> {

                courseMapObj.threeUnit_GradeMap["E"]?.let { coursePointObj.threeUnitE.add(it) }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "F", courseUnit = 3) -> {

                courseMapObj.threeUnit_GradeMap["F"]?.let { coursePointObj.threeUnitF.add(it) }

            }


            //For two unit courses:

            GpData(courseCode = courseData.courseCode, "A", 2) -> {

                courseMapObj.twoUnit_GradeMap["A"]?.let {

                    coursePointObj.twoUnitA.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, "B", 2) -> {

                courseMapObj.twoUnit_GradeMap["B"]?.let {

                    coursePointObj.twoUnitB.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, "C", 2) -> {

                courseMapObj.twoUnit_GradeMap["C"]?.let {

                    coursePointObj.twoUnitC.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, "D", 2) -> {

                courseMapObj.twoUnit_GradeMap["D"]?.let {

                    coursePointObj.twoUnitD.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, "E", 2) -> {

                courseMapObj.twoUnit_GradeMap["E"]?.let {

                    coursePointObj.twoUnitE.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, "A", 2) -> {

                courseMapObj.twoUnit_GradeMap["F"]?.let {

                    coursePointObj.twoUnitF.add(it)
                }

            }

            //For one unit Courses

            GpData(courseCode = courseData.courseCode, courseGrade = "A", courseUnit = 1) -> {

                courseMapObj.oneUnit_GradeMap["A"]?.let {
                    coursePointObj.oneUnitA.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "B", courseUnit = 1) -> {

                courseMapObj.oneUnit_GradeMap["B"]?.let {
                    coursePointObj.oneUnitB.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "C", courseUnit = 1) -> {

                courseMapObj.oneUnit_GradeMap["C"]?.let {
                    coursePointObj.oneUnitC.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "D", courseUnit = 1) -> {

                courseMapObj.oneUnit_GradeMap["D"]?.let {
                    coursePointObj.oneUnitD.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "E", courseUnit = 1) -> {

                courseMapObj.oneUnit_GradeMap["E"]?.let {
                    coursePointObj.oneUnitE.add(it)
                }

            }

            GpData(courseCode = courseData.courseCode, courseGrade = "F", courseUnit = 1) -> {

                courseMapObj.oneUnit_GradeMap["F"]?.let {
                    coursePointObj.oneUnitF.add(it)
                }

            }


        }


    }

}

fun enterYourCourses(noOfCourses: Int) {


    println("Total Courses: $noOfCourses")
    var decrementing = noOfCourses

    for (i in 0 until noOfCourses) {

        println("Enter Your Course Code:")
        val courseCode = readlnOrNull().toString().uppercase(Locale.getDefault())
        println("Enter the Course grade:")
        val courseGrade = readlnOrNull().toString().uppercase(Locale.getDefault())
        println("Enter the course unit:")
        val courseUnit = readlnOrNull().toString().toInt()
        coursesDataEntryObj.coursesDataEntry.add(
            GpData(
                courseCode = courseCode,
                courseGrade = courseGrade,
                courseUnit = courseUnit
            )
        )
        decrementing -= 1
        if (decrementing == 1) {

            println("You have: $decrementing more entry to make...")

        } else if (decrementing > 1) {
            println("You have: $decrementing more entries to make...")

        } else if (decrementing == 0) {
            println("congratulations you have completed all your $noOfCourses entries ")

        }

    }


}




