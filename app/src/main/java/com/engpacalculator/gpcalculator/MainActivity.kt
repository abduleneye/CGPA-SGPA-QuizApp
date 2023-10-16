package com.engpacalculator.gpcalculator

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.gpcalculator_view_model
import com.engpacalculator.gpcalculator.presentation.navigation.SetUpNavGraph
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.GpCalculatorTheme
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : ComponentActivity(), OnBackPressedDispatcherOwner {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobileAds.initialize(this)
            FirebaseAnalytics.getInstance(this)


            var viewModel = viewModel<gpcalculator_view_model>()
            val state by viewModel.dbState.collectAsState()
            val statetwo by viewModel.courseEntries.collectAsState()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            BackHandler(enabled = true, onBack = {


                if (navBackStackEntry?.destination?.route == null) {

                    if (doubleBackToExitPressedOnce) {
                        finish()
                    } else {
                        doubleBackToExitPressedOnce = true
                        (Toast.makeText(
                            this,
                            "Press back button again to exit",
                            Toast.LENGTH_SHORT
                        )
                            .show())

                        Handler(mainLooper).postDelayed(
                            { doubleBackToExitPressedOnce = false }, 2000
                        )
                    }


                } else {

                }

            })




            GpCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppBars
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SetUpNavGraph(
                            navController = navController,
                            gpcalculatorViewModel = viewModel
                        )


                    }
                }
            }


        }
    }

//    override fun onBackPressed() {
//        onBackPressedDispatcher.onBackPressed()
//    }

    companion object {
        private var doubleBackToExitPressedOnce = false
    }


}



