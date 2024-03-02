package com.engpacalculator.gpcalculator

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.core.navigation.SetUpNavGraph
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel
import com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation.DemoQuizViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.GpCalculatorTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity(), OnBackPressedDispatcherOwner {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @OptIn(DelicateCoroutinesApi::class, ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            firebaseAnalytics = FirebaseAnalytics.getInstance(this)


            MobileAds.initialize(this)


            val token = FirebaseMessaging.getInstance().token

            Log.d(" MyToken", token.toString())


            val fiveGpaViewModel: FiveGpaViewModel = hiltViewModel()
            val fourGpaViewModel: FourGpaViewModel = hiltViewModel()
            val demoQuizViewModel: DemoQuizViewModel = hiltViewModel()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()


            BackHandler(enabled = true, onBack = {


                if (navBackStackEntry?.destination?.route == Screen.Home.route) {

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
                            fiveGpaViewModel = fiveGpaViewModel,
                            fourGpaViewModel = fourGpaViewModel,
                            demoQuizViewModel = demoQuizViewModel,
                            mFirebaseAnalytics = firebaseAnalytics
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



