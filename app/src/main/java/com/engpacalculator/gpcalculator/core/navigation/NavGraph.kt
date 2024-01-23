package com.engpacalculator.gpcalculator.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.engpacalculator.gpcalculator.HomeScreen.HomeScreen
import com.engpacalculator.gpcalculator.about_screen_components.ui.theme.AboutScreen
import com.engpacalculator.gpcalculator.core.AnimatedSplash
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_main_screen_components.FiveCgpaMainScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component.FiveCgpaFullResultScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component.FiveCgpaResultRecordScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaFullResultScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaResultRecordScreen
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_main_screen_components.FourCgpaMainScreen
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component.FourCgpaFullResultScreen
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component.FourCgpaResultRecordScreen
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.four_sgpa_results_record_screen_component.FourSgpaFullResultScreen
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.four_sgpa_results_record_screen_component.FourSgpaResultRecordScreen
import com.engpacalculator.gpcalculator.five_grading_system_top_level_components.Five_Grading_System_Mode_Screen
import com.engpacalculator.gpcalculator.four_grading_system_top_level_components.Four_Grading_System_Mode_Screen
import com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component.FiveSgpaMainScreen
import com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component.FourSgpaMainScreen
import com.engpacalculator.gpcalculator.quiz_top_level_components.Quiz_Mode_Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    fiveGpaViewModel: FiveGpaViewModel,
    fourGpaViewModel: FourGpaViewModel,


    ) {
    //val navBackStackEntry by navController.currentBackStackEntryAsState()
    val fiveSgpaViewModel = fiveGpaViewModel
    val fourSgpaViewModel = fourGpaViewModel
    // var fiveCgpaViewModel = fiveCgpaViewModel
    val fiveSgpaUiStates by fiveSgpaViewModel.dbState.collectAsState()
    val fourSgpaUiStates by fourSgpaViewModel.dbState.collectAsState()

    val fiveCgpaUiStates by fiveSgpaViewModel.fiveCgpaUiState.collectAsState()
    val fourCgpaUiStates by fourSgpaViewModel.fourCgpaUiState.collectAsState()
    val fiveSgpaCourseEntriesState by fiveSgpaViewModel.courseEntries.collectAsState()
    val fourSgpaCourseEntriesState by fourSgpaViewModel.courseEntries.collectAsState()
    val fiveSgpaResultFromDBStates by fiveSgpaViewModel.fiveSgparesultIntroDB.collectAsState()
    val fourSgpaResultFromDBStates by fourSgpaViewModel.fourSgparesultIntroDB.collectAsState()
    val fourCgpaResultFromDBStates by fourSgpaViewModel.fourCgpaResultIntroDB.collectAsState()
    val fiveCgpaResultFromDBStates by fiveSgpaViewModel.fiveCgpaResultIntroDB.collectAsState()
    val fiveSgpaRecordsToBeDisplayedForCgpa by fiveSgpaViewModel.fiveCgpaUiState.collectAsState()
    val fourSgpaRecordsToBeDisplayedForCgpa by fourSgpaViewModel.fourCgpaUiState.collectAsState()

    // val fiveCgpaUiListStates by fiveGpaViewModel.fiveCgpaUiStateList.collectAsState()


//    for (i in 1..stateThree.resultItems.size) {
//        stateThree.resultItems[i].resultName
//    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {

            AnimatedSplash(navController = navController)

        }

        composable(route = Screen.Home.route) {

            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {

                HomeScreen(
                    navController = navController,
                    onEvent = fiveSgpaViewModel::onEvent,
                    state = fiveSgpaUiStates,
                    adId = "ca-app-pub-3940256099942544/6300978111"
                )


            }


        }

        composable(route = Screen.About.route) {
            AboutScreen(
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                //ca-app-pub-3656021994888380/4847206452
                // Test Ad unit ca-app-pub-3940256099942544/6300978111

                state = fiveSgpaUiStates,
                onEvent = fiveSgpaViewModel::onEvent


            )
        }



        composable(route = Screen.Five_Screen.route) {
            Five_Grading_System_Mode_Screen(
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fiveSgpaUiStates,
                onEvent = fiveSgpaViewModel::onEvent
            )
        }

        composable(route = Screen.Five_Sgpa_Screen.route) {
            FiveSgpaMainScreen(
                onEvent = fiveSgpaViewModel::onEvent,
                state = fiveSgpaUiStates,
                stateTwo = fiveSgpaCourseEntriesState,
                calcViewModel = fiveSgpaViewModel,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111"
                //ca-app-pub-3656021994888380/3450364305
                // Test Ad unit ca-app-pub-3940256099942544/6300978111
            )

        }

        composable(route = Screen.Four_Sgpa_Screen.route) {
            FourSgpaMainScreen(
                onEvent = fourSgpaViewModel::onEvent,
                state = fourSgpaUiStates,
                stateTwo = fourSgpaCourseEntriesState,
                calcViewModel = fourSgpaViewModel,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111"
                //ca-app-pub-3656021994888380/3450364305
                // Test Ad unit ca-app-pub-3940256099942544/6300978111
            )

        }

        composable(route = Screen.Five_Cgpa_Screen.route) {
            FiveCgpaMainScreen(
                onEvent = fiveSgpaViewModel::onEvent,
                fiveSgpaUiStates = fiveSgpaUiStates,
                fiveCgpaHelperRecordsState = fiveSgpaRecordsToBeDisplayedForCgpa,
                fiveCgpaUiStatesFromSgpaViewModel = fiveSgpaRecordsToBeDisplayedForCgpa,
                stateTwo = fiveSgpaCourseEntriesState,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                fiveGpaViewModel = fiveSgpaViewModel,
                fiveCgpaUiStates = fiveCgpaUiStates,
            )
        }

        composable(route = Screen.Four_Cgpa_Screen.route) {
            FourCgpaMainScreen(
                onEvent = fourSgpaViewModel::onEvent,
                fourSgpaUiStates = fourSgpaUiStates,
                fourCgpaHelperRecordsState = fourSgpaRecordsToBeDisplayedForCgpa,
                fourCgpaUiStatesFromSgpaViewModel = fourSgpaRecordsToBeDisplayedForCgpa,
                stateTwo = fourSgpaCourseEntriesState,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                fourGpaViewModel = fourSgpaViewModel,
                fourCgpaUiStates = fourCgpaUiStates,
            )
        }


        composable(route = Screen.Quiz_Mode_Screen.route) {
            Quiz_Mode_Screen(
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fiveSgpaUiStates, onEvent = fiveSgpaViewModel::onEvent
            )
        }

        composable(route = Screen.Four_Screen.route) {
            Four_Grading_System_Mode_Screen(
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fourSgpaUiStates,
                onEvent = fourSgpaViewModel::onEvent
            )
        }

        composable(
            route = Screen.Five_Sgpa_Records_Screen.route
            //            + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}"
        ) {
            FiveSgpaResultRecordScreen(
                navController = navController,
                fiveSgpaResultRecordState = fiveSgpaResultFromDBStates,
                viewModel = fiveSgpaViewModel,
                onEvent = fiveSgpaViewModel::onEvent,
                state = fiveSgpaUiStates,
                adId = "ca-app-pub-3940256099942544/6300978111"
            )
        }



        composable(
            route = Screen.Four_Sgpa_Records_Screen.route
            //            + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}"
        ) {
            FourSgpaResultRecordScreen(
                navController = navController,
                fourSgpaResultRecordState = fourSgpaResultFromDBStates,
                viewModel = fourSgpaViewModel,
                onEvent = fourSgpaViewModel::onEvent,
                state = fourSgpaUiStates,
                adId = "ca-app-pub-3940256099942544/6300978111"
            )
        }

        composable(
            route = Screen.Four_Cgpa_Records_Screen.route
            //            + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}"
        ) {
            FourCgpaResultRecordScreen(
                navController = navController,
                fourCgpaResultRecordState = fourCgpaResultFromDBStates,
                viewModel = fourSgpaViewModel,
                onEvent = fourSgpaViewModel::onEvent,
                state = fourSgpaUiStates,
                adId = "ca-app-pub-3940256099942544/6300978111"
            )
        }


        composable(
            route = Screen.Five_Cgpa_Records_Screen.route
            //            + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}"
        ) {
            FiveCgpaResultRecordScreen(
                navController = navController,
                fiveCgpaResultRecordState = fiveCgpaResultFromDBStates,
                viewModel = fiveSgpaViewModel,
                onEvent = fiveSgpaViewModel::onEvent,
                state = fiveSgpaUiStates,
                adId = "ca-app-pub-3940256099942544/6300978111"


            )
        }

        composable(
            route = Screen.Five_Sgpa_Full_Records_Screen.route + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}",
            arguments = listOf(
                navArgument(name = "ResultName") {
                    type = NavType.StringType
                    defaultValue = "ResultName"

                },
                navArgument(name = "ListOfCourseDetails") {
                    type = NavType.StringType
                    defaultValue = "Results..."

                },
                navArgument(name = "Gp") {
                    type = NavType.StringType
                    defaultValue = "GradePoint"

                },
                navArgument(name = "ResultRemark") {
                    type = NavType.StringType
                    defaultValue = "Result remark"

                }
            )
        ) { entry ->
            FiveSgpaFullResultScreen(
                resultName = entry.arguments?.getString("ResultName"),
                actualResults = entry.arguments?.getString("ListOfCourseDetails"),
                gP = entry.arguments?.getString("Gp"),
                resultRemark = entry.arguments?.getString("ResultRemark"),
                onEvent = fiveSgpaViewModel::onEvent,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fiveSgpaUiStates

            )
        }

        composable(
            route = Screen.Four_Sgpa_Full_Records_Screen.route + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}",
            arguments = listOf(
                navArgument(name = "ResultName") {
                    type = NavType.StringType
                    defaultValue = "ResultName"

                },
                navArgument(name = "ListOfCourseDetails") {
                    type = NavType.StringType
                    defaultValue = "Results..."

                },
                navArgument(name = "Gp") {
                    type = NavType.StringType
                    defaultValue = "GradePoint"

                },
                navArgument(name = "ResultRemark") {
                    type = NavType.StringType
                    defaultValue = "Result remark"

                }
            )
        ) { entry ->
            FourSgpaFullResultScreen(
                resultName = entry.arguments?.getString("ResultName"),
                actualResults = entry.arguments?.getString("ListOfCourseDetails"),
                gP = entry.arguments?.getString("Gp"),
                resultRemark = entry.arguments?.getString("ResultRemark"),
                onEvent = fourSgpaViewModel::onEvent,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fourSgpaUiStates

            )
        }

        composable(
            route = Screen.Four_Cgpa_Full_Records_Screen.route + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}",
            arguments = listOf(
                navArgument(name = "ResultName") {
                    type = NavType.StringType
                    defaultValue = "ResultName"

                },
                navArgument(name = "ListOfCourseDetails") {
                    type = NavType.StringType
                    defaultValue = "Results..."

                },
                navArgument(name = "Gp") {
                    type = NavType.StringType
                    defaultValue = "GradePoint"

                },
                navArgument(name = "ResultRemark") {
                    type = NavType.StringType
                    defaultValue = "Result remark"

                }
            )
        ) { entry ->
            FourCgpaFullResultScreen(
                resultName = entry.arguments?.getString("ResultName"),
                actualResults = entry.arguments?.getString("ListOfCourseDetails"),
                gP = entry.arguments?.getString("Gp"),
                resultRemark = entry.arguments?.getString("ResultRemark"),
                onEvent = fourSgpaViewModel::onEvent,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fourSgpaUiStates

            )
        }



        composable(
            route = Screen.Five_Cgpa_Full_Records_Screen.route + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}",
            arguments = listOf(
                navArgument(name = "ResultName") {
                    type = NavType.StringType
                    defaultValue = "ResultName"

                },
                navArgument(name = "ListOfCourseDetails") {
                    type = NavType.StringType
                    defaultValue = "Results..."

                },
                navArgument(name = "Gp") {
                    type = NavType.StringType
                    defaultValue = "GradePoint"

                },
                navArgument(name = "ResultRemark") {
                    type = NavType.StringType
                    defaultValue = "Result remark"

                }
            )
        ) { entry ->
            FiveCgpaFullResultScreen(
                resultName = entry.arguments?.getString("ResultName"),
                actualResults = entry.arguments?.getString("ListOfCourseDetails"),
                gP = entry.arguments?.getString("Gp"),
                resultRemark = entry.arguments?.getString("ResultRemark"),
                onEvent = fiveSgpaViewModel::onEvent,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                state = fiveSgpaUiStates
            )
        }


    }

}