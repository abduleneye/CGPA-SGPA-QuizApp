package com.engpacalculator.gpcalculator.presentation.navigation

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
import com.engpacalculator.gpcalculator.presentation.AnimatedSplash
import com.engpacalculator.gpcalculator.presentation.about_screen_components.ui.theme.AboutScreen
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.gpcalculator_view_model
import com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    gpcalculatorViewModel: gpcalculator_view_model

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var viewModel = viewModel<gpcalculator_view_model>()
    val state by viewModel.dbState.collectAsState()
    val statetwo by viewModel.courseEntries.collectAsState()

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
                MainScreen(
                    onEvent = viewModel::onEvent,
                    state = state,
                    stateTwo = statetwo,
                    calcViewModel = gpcalculatorViewModel,
                    navController = navController,
                    adId = "ca-app-pub-3656021994888380/3450364305"
                    //ca-app-pub-3656021994888380/3450364305
                )

            }


        }

        composable(route = Screen.About.route) {
            AboutScreen(
                navController = navController,
                adId = "ca-app-pub-3656021994888380/4847206452",
                //ca-app-pub-3656021994888380/4847206452
                state = state,
                onEvent = viewModel::onEvent


            )
        }


    }

}