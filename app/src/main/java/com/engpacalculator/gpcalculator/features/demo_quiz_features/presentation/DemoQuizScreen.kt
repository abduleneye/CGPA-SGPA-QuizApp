package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import Quiz.Data.Presentation.Domain.QuizFormat
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.R
import com.engpacalculator.gpcalculator.core.ads_components.FiveScreensBottomBannerAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


var questions: ArrayList<QuizFormat> = ArrayList()

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DemoQuizScreen(
    category: String,
    navController: NavController?,
    adId: String?,
    state: FiveSgpaUiStates?,
    quizUiState: DemoQuizUiState,
    onEvent: ((FiveGpaUiEvents) -> Unit),
    onNewEvent: ((DemoQuizUiEventClass) -> Unit)


) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val helpCategory by remember {
        mutableStateOf(category)
    }

    var initialApiCalled by rememberSaveable {
        mutableStateOf(false)
    }


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

                actions = {
                    IconButton(
                        onClick = {

                            onNewEvent(
                                DemoQuizUiEventClass.loadData(
                                    category = quizUiState.questionCategory,
                                    amount = quizUiState.amountOfQuestions
                                ),
                            )

                            // onNewEvent(DemoQuizUiEventClass.resetQuestionIndex)


                        },


                        ) {
                        Column {
                            androidx.compose.material.Icon(
                                painter = painterResource(id = R.drawable.app_update_icon),
                                contentDescription = "Update App"
                            )

                        }


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
    ) {

        if (!initialApiCalled) {
            LaunchedEffect(key1 = true) {
                scope.launch {
                    when (helpCategory) {
                        "Sciences" -> {
                            onNewEvent(DemoQuizUiEventClass.setQuestionDetailsForReload("17", "50"))
                            onNewEvent(DemoQuizUiEventClass.loadData("17", "50"))
                        }

                        "History" -> {

                            onNewEvent(DemoQuizUiEventClass.setQuestionDetailsForReload("23", "10"))
                            onNewEvent(DemoQuizUiEventClass.loadData("23", "10"))


                        }

                        "Art" -> {
                            onNewEvent(DemoQuizUiEventClass.setQuestionDetailsForReload("25", "10"))
                            onNewEvent(DemoQuizUiEventClass.loadData("25", "10"))


                        }

                        "Mythology" -> {

                            onNewEvent(DemoQuizUiEventClass.setQuestionDetailsForReload("20", "10"))
                            onNewEvent(DemoQuizUiEventClass.loadData("20", "10"))


                        }
                    }
                }
                initialApiCalled = true


            }


        }


        if (quizUiState.screenStatus == "Is Loading") {
            Box(
                modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = Cream)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator(color = AppBars)

                }

            }

        } else if (quizUiState.screenStatus == "Loaded Successfully") {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Cream)
            ) {

//                var quizUiState.questionIndex by remember {
//                    mutableIntStateOf(0)
//                }
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
                                .background(color = Cream),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = category,
                                fontWeight = FontWeight.Bold
                            )


                            if (category != null) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Total questions:  ${quizUiState.questions.size}",
                                    )
                                    Text(
                                        text = "Score: " + " ${quizUiState.currentScore}",
                                    )


                                }
                            }

                            Card(
                                elevation = CardDefaults.cardElevation(8.dp),
                                //shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(0.9f)
                                    .padding(top = 8.dp)
                                    .background(color = Cream),


                                ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = Cream),
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.CenterHorizontally

                                ) {
                                    Text(
                                        text = "${quizUiState.questions[quizUiState.questionIndex].question}",
                                        modifier = Modifier
                                            //.weight(0.9f)
                                            .padding(all = 4.dp),
                                        fontWeight = FontWeight.Bold

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


                                var context = LocalContext.current
                                var scope = rememberCoroutineScope()

                                var selectedOption = rememberSaveable {
                                    mutableStateOf("")
                                }


                                LazyColumn(
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                                ) {
                                    item {
                                        quizUiState.optionsList
                                            .forEachIndexed { index, info ->
                                                Spacer(modifier = Modifier.height(24.dp))
                                                Card(
                                                    elevation = CardDefaults.cardElevation(8.dp),
                                                    shape = RoundedCornerShape(10.dp),
                                                    modifier = Modifier
                                                        .height(64.dp)
                                                        .fillMaxWidth(0.9f)
                                                        .background(color = Cream)
                                                ) {
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier
                                                            .fillMaxSize()
                                                            .background(color = Cream)
                                                            .clickable(enabled = quizUiState.isRadiobuttonEnabled) {
                                                                selectedOption.value = info
                                                                onNewEvent(
                                                                    DemoQuizUiEventClass.setSelectedOption(
                                                                        selectedOption.value
                                                                    )
                                                                )

                                                                onNewEvent(DemoQuizUiEventClass.disableRadioButton)
                                                                onNewEvent(DemoQuizUiEventClass.enableNextButton)

                                                                Log.d(
                                                                    "BEFORE_IF_QUESTION_TAG",
                                                                    "SelectedAns: ${selectedOption.value}\n" +
                                                                            "CorrectAns: ${
                                                                                quizUiState.questions.get(
                                                                                    quizUiState.questionIndex
                                                                                ).correct_answer
                                                                            }"
                                                                )
                                                                if (selectedOption.value == quizUiState.questions.get(
                                                                        quizUiState.questionIndex
                                                                    ).correct_answer
                                                                ) {
                                                                    Log.d(
                                                                        "IN_IF_QUESTION_TAG",
                                                                        "SelectedAns: ${quizUiState.selectedOption}\n" +
                                                                                "CorrectAns: ${
                                                                                    quizUiState.questions.get(
                                                                                        quizUiState.questionIndex
                                                                                    ).correct_answer
                                                                                }"
                                                                    )

                                                                    onNewEvent(DemoQuizUiEventClass.currentScore)
                                                                    onNewEvent(
                                                                        DemoQuizUiEventClass.setQuestionAnsweredStatus(
                                                                            "correct"
                                                                        )
                                                                    )
                                                                } else {

                                                                    Log.d(
                                                                        "ELSE_QUESTION_TAG",
                                                                        "SelectedAns: ${quizUiState.selectedOption}\n" +
                                                                                "CorrectAns: ${
                                                                                    quizUiState.questions.get(
                                                                                        quizUiState.questionIndex
                                                                                    ).correct_answer
                                                                                }"
                                                                    )


                                                                    onNewEvent(
                                                                        DemoQuizUiEventClass.setQuestionAnsweredStatus(
                                                                            "wrong"
                                                                        )
                                                                    )


                                                                }

                                                                scope.launch {
                                                                    delay(1000)
                                                                    onNewEvent(DemoQuizUiEventClass.showCorrectnessDiaogBox)
                                                                }

                                                            }


                                                    ) {

                                                        Text(
                                                            text = info,
                                                            modifier = Modifier
                                                                .weight(0.9f)
                                                                .padding(start = 8.dp),
                                                            fontWeight = FontWeight.Bold,
                                                        )
                                                        RadioButton(
                                                            enabled = quizUiState.isRadiobuttonEnabled,
                                                            selected = selectedOption.value == info,
                                                            onClick = {

                                                                selectedOption.value = info
                                                                onNewEvent(
                                                                    DemoQuizUiEventClass.setSelectedOption(
                                                                        selectedOption.value
                                                                    )
                                                                )

                                                                onNewEvent(DemoQuizUiEventClass.disableRadioButton)
                                                                onNewEvent(DemoQuizUiEventClass.enableNextButton)

                                                                Log.d(
                                                                    "BEFORE_IF_QUESTION_TAG",
                                                                    "SelectedAns: ${selectedOption.value}\n" +
                                                                            "CorrectAns: ${
                                                                                quizUiState.questions.get(
                                                                                    quizUiState.questionIndex
                                                                                ).correct_answer
                                                                            }"
                                                                )
                                                                if (selectedOption.value == quizUiState.questions.get(
                                                                        quizUiState.questionIndex
                                                                    ).correct_answer
                                                                ) {
                                                                    Log.d(
                                                                        "IN_IF_QUESTION_TAG",
                                                                        "SelectedAns: ${quizUiState.selectedOption}\n" +
                                                                                "CorrectAns: ${
                                                                                    quizUiState.questions.get(
                                                                                        quizUiState.questionIndex
                                                                                    ).correct_answer
                                                                                }"
                                                                    )
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Correct!!!",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                    onNewEvent(DemoQuizUiEventClass.currentScore)
                                                                    onNewEvent(
                                                                        DemoQuizUiEventClass.setQuestionAnsweredStatus(
                                                                            "correct"
                                                                        )
                                                                    )
                                                                } else {

                                                                    Log.d(
                                                                        "ELSE_QUESTION_TAG",
                                                                        "SelectedAns: ${quizUiState.selectedOption}\n" +
                                                                                "CorrectAns: ${
                                                                                    quizUiState.questions.get(
                                                                                        quizUiState.questionIndex
                                                                                    ).correct_answer
                                                                                }"
                                                                    )

                                                                    Toast.makeText(
                                                                        context,
                                                                        "Wrong!!! the correct answer is ${
                                                                            quizUiState.questions.get(
                                                                                quizUiState.questionIndex
                                                                            ).correct_answer
                                                                        }",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()

                                                                    onNewEvent(
                                                                        DemoQuizUiEventClass.setQuestionAnsweredStatus(
                                                                            "wrong"
                                                                        )
                                                                    )


                                                                }

                                                                scope.launch {
                                                                    delay(1000)
                                                                    onNewEvent(DemoQuizUiEventClass.showCorrectnessDiaogBox)

                                                                }


                                                            },
                                                            modifier = Modifier.weight(0.1f)
                                                        )


                                                    }
                                                }

                                            }
                                        Spacer(modifier = Modifier.height(4.dp))

                                        Button(

                                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                                containerColor = AppBars
                                            ),
                                            onClick = {

                                                onNewEvent(DemoQuizUiEventClass.enableRadioButton)
                                                onNewEvent(DemoQuizUiEventClass.disableNextButton)
                                                onNewEvent(DemoQuizUiEventClass.incrementQuestionIndex)
                                                selectedOption.value = ""

                                                if (quizUiState.questionIndex - 1 == quizUiState.questions.size) {
                                                    Toast.makeText(
                                                        context,
                                                        "End of questions",
                                                        Toast.LENGTH_LONG
                                                    ).show()

                                                }

                                            },
                                            enabled = quizUiState.isNextButtonEnabled
                                        ) {

                                            Text(text = "next")

                                        }
                                    }


                                }

                                // Spacer(modifier = Modifier.height(4.dp))


                            }


                        }


                    }


                }

            }


        } else if (quizUiState.screenStatus == "An Error occurred") {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Cream)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text("An error occurred please check your network!!!")
                    Spacer(
                        modifier = Modifier
                            .height(24.dp)
                    )

                    Button(

                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = AppBars
                        ),

                        onClick = {
                            onNewEvent(
                                DemoQuizUiEventClass.loadData(
                                    category = quizUiState.questionCategory,
                                    amount = quizUiState.amountOfQuestions
                                ),
                            )

                        }) {

                        Text(text = "Retry")

                    }


                }

            }
        }

        if (quizUiState.correctnessDialogBoxVisibility == true) {

            DemoQuizCorrectnessDialogBox(onEvent = onNewEvent, demoQuizUiState = quizUiState)

        }

    }

}


fun optionShuffler(
    quizUiState: DemoQuizUiState
): List<String> {
    val shuffledOptions = quizUiState.questions.get(quizUiState.questionIndex).answers.shuffled()
    return shuffledOptions
}










