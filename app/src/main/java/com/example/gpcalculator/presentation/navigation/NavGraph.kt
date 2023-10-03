package com.example.gpcalculator.presentation.navigation

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
import com.example.gpcalculator.presentation.AnimatedSplash
import com.example.gpcalculator.presentation.about_screen_components.ui.theme.AboutScreen
import com.example.gpcalculator.presentation.course_list_screen_components.gpcalculator_view_model
import com.example.gpcalculator.presentation.myViewModels.course_list_screen_component.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    gpcalculatorViewModel: gpcalculator_view_model

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {

            AnimatedSplash(navController = navController)

        }

        composable(route = Screen.Home.route) {
            var viewModel = viewModel<gpcalculator_view_model>()
            val state by viewModel.dbState.collectAsState()
            val statetwo by viewModel.courseEntries.collectAsState()
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
                    adId = "ca-app-pub-3940256099942544/6300978111"
                )

            }


        }

        composable(route = Screen.About.route) {
            AboutScreen(
                navController = navController,
                adId = "ca-app-pub-3940256099942544/6300978111"

            )
        }


    }

}