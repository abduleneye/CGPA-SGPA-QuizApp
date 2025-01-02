package com.eneye.enquizgpa

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eneye.enquizgpa.core.navigation.Screen
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun DefaultCardSample(
    textOneInCardBox: String,
    textTwoInCardBox: String,
    navController: NavController?,
    route: String?,
    modifier: Modifier,
    mFirebaseAnalytics: FirebaseAnalytics,
) {

    val context = LocalContext.current

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier

    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Cream)
                .clickable {
                    if (route != null) {
                        when (textOneInCardBox) {
                            "Sciences".uppercase() -> {
                                val params = Bundle()
                                params.putString("SciencesQuizCard", "SciencesQuizCardClicked")
                                mFirebaseAnalytics.logEvent("sciencesQuizCard", params)

                            }

                            "Art".uppercase() -> {
                                val params = Bundle()
                                params.putString("ArtQuizCard", "ArtQuizCardClicked")
                                mFirebaseAnalytics.logEvent("artQuizCard", params)

                            }

                            "History".uppercase() -> {
                                val params = Bundle()
                                params.putString("HistoryQuizCard", "HistoryQuizCardClicked")
                                mFirebaseAnalytics.logEvent("historyQuizCard", params)

                            }

                            "Mythology".uppercase() -> {
                                val params = Bundle()
                                params.putString("MythologyQuizCard", "MythologyQuizCardClicked")
                                mFirebaseAnalytics.logEvent("mythologyQuizCard", params)

                            }

                        }
                        when (route) {
                            Screen.Quiz_Legit_Screen.route -> {
                                val params = Bundle()
                                params.putString("RewardingCard", "RewardingQuizCardClicked")
                                mFirebaseAnalytics.logEvent("rewardingQuizCard", params)

                                Toast
                                    .makeText(
                                        context,
                                        "Under Development," +
                                                "Still Cooking...",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }

                            Screen.Demo_Quiz.route -> {
                                val params = Bundle()
                                params.putString("DemoQuizCard", "DemoQuizCardClicked")
                                mFirebaseAnalytics.logEvent("demoQuizCard", params)
                                navController?.navigate(route)


                            }

                            Screen.Quiz_Mode_Screen.route -> {
                                val params = Bundle()
                                params.putString("QuizSectionCard", "QuizSectionCardClicked")
                                mFirebaseAnalytics.logEvent("quizSectionCard", params)
                                navController?.navigate(route)


                            }

                            Screen.Five_Screen.route -> {
                                val params = Bundle()
                                params.putString("FiveGpaCard", "FiveGpaCardClicked")
                                mFirebaseAnalytics.logEvent("fiveGpaScreen", params)
                                navController?.navigate(route)

                            }

                            Screen.Five_Sgpa_Screen.route -> {
                                val params = Bundle()
                                params.putString("FiveSgpaCard", "FiveSgpaCardClicked")
                                mFirebaseAnalytics.logEvent("fiveSgpaCard", params)
                                navController?.navigate(route)

                            }

                            Screen.Five_Cgpa_Screen.route -> {
                                val params = Bundle()
                                params.putString("FiveCgpaCard", "FiveCgpaCardClicked")
                                mFirebaseAnalytics.logEvent("fiveCgpaCard", params)
                                navController?.navigate(route)

                            }

                            Screen.Four_Screen.route -> {
                                val params = Bundle()
                                params.putString("FourGpaCard", "FourGpaCardClicked")
                                mFirebaseAnalytics.logEvent("fourGpaScreen", params)
                                navController?.navigate(route)

                            }

                            Screen.Four_Sgpa_Screen.route -> {
                                val params = Bundle()
                                params.putString("FourSgpaCard", "FourSgpaCardClicked")
                                mFirebaseAnalytics.logEvent("fourSgpaCard", params)
                                navController?.navigate(route)

                            }

                            Screen.Four_Cgpa_Screen.route -> {
                                val params = Bundle()
                                params.putString("FourCgpaCard", "FourCgpaCardClicked")
                                mFirebaseAnalytics.logEvent("fourCgpaCard", params)
                                navController?.navigate(route)

                            }

                            Screen.About.route -> {
                                val params = Bundle()
                                params.putString("AboutCard", "AboutCardClicked")
                                mFirebaseAnalytics.logEvent("aboutCard", params)
                                navController?.navigate(route)

                            }

                            else -> {

                                navController?.navigate(route)

                            }


                        }

                    }
                },
        ) {

            Text(
                text = textOneInCardBox,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp


            )
            Text(
                text = textTwoInCardBox,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(4.dp),
                fontSize = 8.sp
            )
        }


    }

}