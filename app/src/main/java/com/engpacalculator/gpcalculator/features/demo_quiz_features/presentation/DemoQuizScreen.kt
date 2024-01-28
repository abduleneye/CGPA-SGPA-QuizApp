package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import Quiz.Data.Presentation.Domain.Questions
import Quiz.Data.Presentation.Domain.QuizFormat
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.ads_components.FiveScreensBottomBannerAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream


data class TogglableInfo(
    val isChecked: Boolean,
    val text: String,


    )

var physics: Questions = Questions()

var questions: ArrayList<QuizFormat> = ArrayList()

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DemoQuizScreen(
    category: String?,
    navController: NavController?,
    adId: String?,
    state: FiveSgpaUiStates?,
    onEvent: ((FiveGpaUiEvents) -> Unit)?,
    quizUiState: DemoQuizUiState

) {

    ///


    questions.add(
        QuizFormat(
            question = "What is a verb?",
            optionA = "An acion word",
            optionB = "A doing word",
            optionC = " a speech",
            optionD = "I dont know",
            correctAnswer = "An Action Word"
        )
    )

    questions.add(
        QuizFormat(
            question = "What is a verb?",
            optionA = "An acion word",
            optionB = "A doing word",
            optionC = " a speech",
            optionD = "I dont know",
            correctAnswer = "An Action Word"
        )
    )

    questions.add(
        QuizFormat(
            question = "What is a verb?",
            optionA = "An acion word",
            optionB = "A doing word",
            optionC = " a speech",
            optionD = "I dont know",
            correctAnswer = "An Action Word"
        )
    )
//    for(i in 0.. questions.size){
//
//        println(questions)
//    }

    println("Fromclass ${physics.physicsQuestions()}")

    ///


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Demo mode")
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Cream)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .background(color = Cream),


                    ) {


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                        //.background(color = Color.Red)
                        ,
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (category != null) {
                            Text(text = category)
                        }

                        Card(
                            elevation = CardDefaults.cardElevation(8.dp),
                            //shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth(0.9f)
                                .padding(top = 8.dp),
                            //.background(color = Cream)


                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                Text(
                                    text = "${quizUiState.questions}",
                                    modifier = Modifier
                                        //.weight(0.9f)
                                        .padding(top = 4.dp)
                                )

                            }


                        }

                        Spacer(modifier = Modifier.height(24.dp))


                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start

                        ) {

                            RadioButtons()
                        }


                    }


                }


            }


        }

    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun RadioButtons() {
    val radioButtons = remember {
        mutableStateListOf(
            TogglableInfo(
                isChecked = false,
                text = "Yuri gagrin"
            ),
            TogglableInfo(
                isChecked = false,
                text = "Mr Ibu"
            ),

            TogglableInfo(
                isChecked = false,
                text = "Niel Armstrong"
            ),
            TogglableInfo(
                isChecked = false,
                text = "Elon musk"
            )
        )

    }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
    ) {
        item {
            radioButtons.forEachIndexed { index, info ->


                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth(0.9f)
                    //.background(color = Cream)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                radioButtons.replaceAll {
                                    it.copy(
                                        isChecked = it.text == info.text
                                    )
                                }
                            }
                    ) {

                        Text(
                            text = info.text, modifier = Modifier
                                .weight(0.9f)
                                .padding(start = 8.dp)
                        )
                        RadioButton(
                            selected = info.isChecked,
                            onClick = {
                                radioButtons.replaceAll {
                                    it.copy(
                                        isChecked = it.text == info.text
                                    )
                                }
                            },
                            modifier = Modifier.weight(0.1f)
                        )


                    }
                }
            }
        }


    }
}