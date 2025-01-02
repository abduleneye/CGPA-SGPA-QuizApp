package com.eneye.enquizgpa.features.demo_quiz_features.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eneye.enquizgpa.DefaultCardSample
import com.eneye.enquizgpa.core.ads_components.FiveScreensBottomBannerAd
import com.eneye.enquizgpa.core.navigation.Screen
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DemoQuizCategoriesScreen(
    navController: NavController?,
    adId: String?,
    state: FiveSgpaUiStates?,
    onEvent: ((FiveGpaUiEvents) -> Unit)?,


    ) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager

    var res by remember {
        mutableStateOf("")
    }



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Demo Categories")
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        //navController.navigate(Screen.Home.route)
                        navController?.popBackStack()
                        //navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arrow"
                        )

                    }
                },


                )


        },


        bottomBar = {

            BottomAppBar(
                containerColor = Cream,
                contentPadding = PaddingValues(0.dp)

            ) {


                if (state != null) {
                    if (onEvent != null) {
                        if (adId != null) {
                            FiveScreensBottomBannerAd(
                                isLoading = state,
                                onEvent = onEvent,
                                contentAfterLoading = {

                                },
                                modifier = Modifier,
                                adId = adId
                            )
                        }
                    }
                }

            }


        }
    ) {

        var scrollState = rememberScrollState()


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // .verticalScroll(state = scrollState)
                .background(color = Cream)
                .padding(it),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = it.calculateTopPadding())
                    //  .padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),

                ) {

                    if (navController != null) {
                        DefaultCardSample(
                            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context),
                            textOneInCardBox = "Sciences".uppercase(),
                            textTwoInCardBox = "(Demo)".uppercase(),
                            navController = navController,
                            route = Screen.Demo_Quiz.withArgs(
                                "Sciences"
                            ),
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(start = 16.dp)


                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))


                    if (navController != null) {
                        DefaultCardSample(
                            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context),
                            textOneInCardBox = "Art".uppercase(),
                            textTwoInCardBox = "(Demo)".uppercase(),
                            navController = navController,
                            route = Screen.Demo_Quiz.withArgs(
                                "Art"
                            ), modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(end = 16.dp)
                        )
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = it.calculateBottomPadding())

                    // .weight(0.5f)

                    //.padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),

                ) {

                    if (navController != null) {
                        DefaultCardSample(
                            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context),
                            textOneInCardBox = "Mythology".uppercase(),
                            textTwoInCardBox = "(Demo)".uppercase(),
                            navController = navController,
                            route = Screen.Demo_Quiz.withArgs(
                                "Mythology"
                            ),
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(start = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))


                    if (navController != null) {
                        DefaultCardSample(
                            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context),
                            textOneInCardBox = "History".uppercase(),
                            textTwoInCardBox = "(Demo)".uppercase(),
                            navController = navController,
                            route = Screen.Demo_Quiz.withArgs(
                                "History"
                            ),
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(end = 16.dp)

                        )
                    }


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
    //HomeScreen(navController = null, null, state = null, onEvent = null)
}