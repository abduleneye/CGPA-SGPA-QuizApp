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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.engpacalculator.gpcalculator.HomeScreen.HomeScreen
import com.engpacalculator.gpcalculator.about_screen_components.ui.theme.AboutScreen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.AnimatedSplash
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.UniFiveSgpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.results_record_screen_component.RecordScreen
import com.engpacalculator.gpcalculator.five_grading_system_top_level_components.Five_Grading_System_Mode
import com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    gpcalculatorViewModel: UniFiveSgpaViewModel

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var viewModel = viewModel<UniFiveSgpaViewModel>()
    val state by viewModel.dbState.collectAsState()
    val statetwo by viewModel.courseEntries.collectAsState()
    val stateThree by viewModel.resultDB.collectAsState()

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

                HomeScreen(navController)


            }


        }

        composable(route = Screen.About.route) {
            AboutScreen(
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111",
                //ca-app-pub-3656021994888380/4847206452
                // Test Ad unit ca-app-pub-3940256099942544/6300978111

                state = state,
                onEvent = viewModel::onEvent


            )
        }

        composable(route = Screen.Five_Sgpa_Records_Screen.route) {
            RecordScreen(
                navController = navController,
                state = stateThree.resultItems,
                viewModel = viewModel
            )
        }

        composable(route = Screen.Five_Screen.route) {
            Five_Grading_System_Mode(navController = navController)
        }

        composable(route = Screen.Five_Sgpa_Screen.route) {
            MainScreen(
                onEvent = viewModel::onEvent,
                state = state,
                stateTwo = statetwo,
                calcViewModel = gpcalculatorViewModel,
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111"
                //ca-app-pub-3656021994888380/3450364305
                // Test Ad unit ca-app-pub-3940256099942544/6300978111
            )

        }


    }

}