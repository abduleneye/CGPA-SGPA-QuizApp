package com.engpacalculator.gpcalculator.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.engpacalculator.gpcalculator.HomeScreen.HomeScreen
import com.engpacalculator.gpcalculator.about_screen_components.ui.theme.AboutScreen
import com.engpacalculator.gpcalculator.core.AnimatedSplash
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.uni_five_cgpa_main_screen_components.FiveCgpaMainScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaFullResultScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaResultRecordScreen
import com.engpacalculator.gpcalculator.five_grading_system_top_level_components.Five_Grading_System_Mode_Screen
import com.engpacalculator.gpcalculator.four_grading_system_top_level_components.Four_Grading_System_Mode_Screen
import com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component.FiveSgpaMainScreen
import com.engpacalculator.gpcalculator.quiz_top_level_components.Quiz_Mode_Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    gpcalculatorViewModel: FiveSgpaViewModel

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var fiveSgpaViewModel = viewModel<FiveSgpaViewModel>()
    val fiveSgpaUiStates by fiveSgpaViewModel.dbState.collectAsState()
    val fiveSgpaCourseEntriesState by fiveSgpaViewModel.courseEntries.collectAsState()
    val fiveSgpaResultFromDBStates by fiveSgpaViewModel.resultIntroDB.collectAsState()
    val fiveSgpaRecordsToBeDisplayedForCgpa by fiveSgpaViewModel.fiveCgpaUiState.collectAsState()
    // val fiveCgpaUiListStates by fiveSgpaViewModel.fiveCgpaUiStateList.collectAsState()


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
                calcViewModel = gpcalculatorViewModel,
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
                fiveSgpaRecordsState = fiveSgpaResultFromDBStates,
                fiveCgpaUiStates = fiveSgpaRecordsToBeDisplayedForCgpa,
                stateTwo = fiveSgpaCourseEntriesState,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                // uniFiveCgpaViewModel =  ,
                fiveSgpaViewModel = fiveSgpaViewModel,
                // onEventFiveCgpa =
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
                state = fiveSgpaUiStates,
                onEvent = fiveSgpaViewModel::onEvent
            )
        }

        composable(
            route = Screen.Five_Sgpa_Records_Screen.route
            //            + "/{ResultName}/{ListOfCourseDetails}/{Gp}/{ResultRemark}"
        ) {
            FiveSgpaResultRecordScreen(
                navController = navController,
                state = fiveSgpaResultFromDBStates,
                viewModel = fiveSgpaViewModel,
                onEvent = fiveSgpaViewModel::onEvent
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
                navController = navController
            )
        }


    }

}