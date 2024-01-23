package com.engpacalculator.gpcalculator.core.navigation

sealed class Screen(val route: String) {
    object Four_Cgpa_Full_Records_Screen : Screen("four_cgpa_full_records_screen")

    object Four_Cgpa_Records_Screen : Screen("four_cgpa_records_screen")

    object Four_Sgpa_Full_Records_Screen : Screen("four_sgpa_full_records_screen")

    object Four_Sgpa_Records_Screen : Screen("four_sgpa_records_screen")


    object Splash : Screen("splash_screen")
    object About : Screen("about_screen")
    object Home : Screen("home_screen")

    object Five_Screen : Screen("five_screen")

    object Five_Sgpa_Screen : Screen("five_sgpa_screen")


    object Five_Cgpa_Screen : Screen("five_cgpa_screen")

    object Four_Screen : Screen("four_screen")


    object Four_Sgpa_Screen : Screen("four_sgpa_screen")

    object Four_Cgpa_Screen : Screen("four_sgpa_screen")

    object Quiz_Mode_Screen : Screen("quiz_mode_screen")

    object Quiz_Demo_Screen : Screen("quiz_demo_screen")
    object Quiz_Legit_Screen : Screen("quiz_legit_screen")


    object Five_Sgpa_Records_Screen :
    //Screen("records_screen/ResultName/ListOfCourseDetails/Gp/ResultRemark")
        Screen("five_sgpa_records_screen")


    object Five_Sgpa_Full_Records_Screen :
        Screen("five_sgpa_full_records_screen")

    object Five_Cgpa_Records_Screen :
    //Screen("records_screen/ResultName/ListOfCourseDetails/Gp/ResultRemark")
        Screen("five_cgpa_records_screen")


    object Five_Cgpa_Full_Records_Screen :
        Screen("full_cgpa_full_records_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)

            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}