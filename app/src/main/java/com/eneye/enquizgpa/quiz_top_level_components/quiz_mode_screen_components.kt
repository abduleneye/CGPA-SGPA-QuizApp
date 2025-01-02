package com.eneye.enquizgpa.quiz_top_level_components

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eneye.enquizgpa.DefaultCardSample
import com.eneye.enquizgpa.core.ads_components.FiveScreensBottomBannerAd
import com.eneye.enquizgpa.core.data_store.data_store_repo.QuizIntroDataStoreRepoVisibility
import com.eneye.enquizgpa.core.navigation.Screen
import com.eneye.enquizgpa.features.demo_quiz_features.presentation.DemoQuizUiEventClass
import com.eneye.enquizgpa.features.demo_quiz_features.presentation.DemoQuizUiState
import com.eneye.enquizgpa.features.demo_quiz_features.presentation.QuizIntroDialogBoxFromDatatStore
import com.eneye.enquizgpa.features.demo_quiz_features.presentation.QuizIntroDialogBoxFromViewModel
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quiz_Mode_Screen(
    navController: NavController?,
    adId: String?,
    state: FiveSgpaUiStates?,
    onEvent: ((FiveGpaUiEvents) -> Unit)?,
    onQuizModeEvent: (DemoQuizUiEventClass) -> Unit,
    quizIntroDBState: DemoQuizUiState,
    mFirebaseAnalytics: FirebaseAnalytics,


    ) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = QuizIntroDataStoreRepoVisibility(context = context)
    val status =
        dataStore.getQuizIntroDialogBoxVisibilityStatus.collectAsState(initial = false).value


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Quiz mode")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),


                navigationIcon = {
                    IconButton(onClick = {
                        //navController.navigate(Screen.Home.route)
                        if (navController != null) {
                            navController.popBackStack()
                        }
                        //navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arrow"
                        )

                    }
                },

                actions = {
                    IconButton(
                        onClick = {
                            val params = Bundle()
                            params.putString(
                                "AboutQuizButton",
                                "AboutQuizButtonClicked"
                            )
                            mFirebaseAnalytics.logEvent("AboutQuizButton", params)
                            onQuizModeEvent(DemoQuizUiEventClass.showIntroDialogBoxVisibilty)

                        },


                        ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info"
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
                        .padding(it)
                ) {

                    if (
                        status
                    ) {
                        QuizIntroDialogBoxFromDatatStore(
                            quizIntroDBState = quizIntroDBState,
                            demoQuizOnEvent = onQuizModeEvent
                        )

                    }

                    if (
                        quizIntroDBState.quizIntroDialogBoxVisibility
                    ) {
                        QuizIntroDialogBoxFromViewModel(
                            quizIntroDBState = quizIntroDBState,
                            demoQuizOnEvent = onQuizModeEvent
                        )

                    }


                    if (navController != null) {
                        DefaultCardSample(
                            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context),
                            textOneInCardBox = "Demo".uppercase(),
                            textTwoInCardBox = "(Mode)".uppercase(),
                            navController = navController,
                            route = Screen.Quiz_Demo_Screen_Categories.route,
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
                            textOneInCardBox = "Rewarding".uppercase(),
                            textTwoInCardBox = "(Mode)".uppercase(),
                            navController = navController,
                            route = Screen.Quiz_Legit_Screen.route,
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
fun Quiz_Mode_Preview(

) {
    //Quiz_Mode_Screen(navController = null, null, null, null)
}