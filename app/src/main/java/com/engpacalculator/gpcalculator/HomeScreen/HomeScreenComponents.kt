package com.engpacalculator.gpcalculator.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.DefaultCardSample
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController?

) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Home ")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),


                )
        },
        bottomBar = {

            BottomAppBar(
                containerColor = Cream,
                contentPadding = PaddingValues(0.dp)

            ) {


//                ShimmerBottomAboutBarItemAd(
//                    isLoading = state,
//                    onEvent = onEvent,
//                    contentAfterLoading = {
//
//                    },
//                    modifier = Modifier,
//                    adId = adId
//                )

            }


        }
    ) {

        var scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),

                ) {

                if (navController != null) {
                    DefaultCardSample(
                        textInCardBox = "5.0 grading system".uppercase(),
                        navController = navController,
                        Screen.Five_Screen.route
                    )
                }
                if (navController != null) {
                    DefaultCardSample(
                        textInCardBox = "4.0 grading system".uppercase(),
                        navController = navController,
                        Screen.Four_Screen.route
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),

                ) {

                if (navController != null) {
                    DefaultCardSample(
                        textInCardBox = "Quiz".uppercase(),
                        navController = navController,
                        Screen.Quiz_Mode_Screen.route
                    )
                }
                if (navController != null) {
                    DefaultCardSample(
                        textInCardBox = "About".uppercase(),
                        navController,
                        Screen.About.route
                    )
                }

            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeScreenPreview(

) {
    HomeScreen(navController = null)
}