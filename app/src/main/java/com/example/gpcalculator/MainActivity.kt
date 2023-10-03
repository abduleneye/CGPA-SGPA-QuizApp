package com.example.gpcalculator

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gpcalculator.presentation.course_list_screen_components.gpcalculator_view_model
import com.example.gpcalculator.presentation.navigation.SetUpNavGraph
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity(), OnBackPressedDispatcherOwner {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MobileAds.initialize(this)

            var viewModel = viewModel<gpcalculator_view_model>()
            val state by viewModel.dbState.collectAsState()
            val statetwo by viewModel.courseEntries.collectAsState()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            

            BackHandler(enabled = true, onBack = {

                val current = navController.previousBackStackEntry?.destination?.route

//                (Toast.makeText(
//                    this,
//                    "on back detected  cur loc:  $current: ${navBackStackEntry?.destination?.route} but",
//                    Toast.LENGTH_SHORT
//                ).show())
                //onBackPressedDispatcher.onBackPressed()
                //finish()

                if (navBackStackEntry?.destination?.route == null) {
//                    (Toast.makeText(
//                        this,
//                        "on home screen ",
//                        Toast.LENGTH_SHORT
//                    ).show())
                    if (doubleBackToExitPressedOnce) {
                        (Toast.makeText(
                            this,
                            "Press back again to exit",
                            Toast.LENGTH_SHORT
                        )
                            .show())
                        finish()
                    } else {
                        doubleBackToExitPressedOnce = true
                        (Toast.makeText(
                            this,
                            "oPress back again to exit",
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

            SetUpNavGraph(navController = navController, gpcalculatorViewModel = viewModel)


        }
    }

//    override fun onBackPressed() {
//        onBackPressedDispatcher.onBackPressed()
//    }

    companion object {
        private var doubleBackToExitPressedOnce = false
    }


}



