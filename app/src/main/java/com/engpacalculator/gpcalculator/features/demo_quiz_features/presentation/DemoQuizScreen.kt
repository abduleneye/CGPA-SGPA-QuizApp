package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import Quiz.Data.Presentation.Domain.QuizFormat
import android.annotation.SuppressLint
import android.os.Build
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.R
import com.engpacalculator.gpcalculator.core.ads_components.FiveScreensBottomBannerAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
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

                        "Commerce" -> {

                            onNewEvent(DemoQuizUiEventClass.setQuestionDetailsForReload("20", "10"))
                            onNewEvent(DemoQuizUiEventClass.loadData("20", "10"))


                        }
                    }
                }
                initialApiCalled = true


            }


        }


        if (quizUiState.isLoading == false) {
            Box(
                modifier =
                Modifier
                    .fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator()

                }

            }

        } else {

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
                            //.background(color = Color.Red)
                            ,
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            if (category != null) {
                                Text(text = category + " ${quizUiState.questions.size}")
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
                                        text = "${quizUiState.questions[quizUiState.questionIndex].question}",
                                        modifier = Modifier
                                            //.weight(0.9f)
                                            .padding(all = 4.dp)
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

                                var selectedOption by remember {
                                    mutableStateOf("")
                                }

                                var isRadioButtonIsEnabled by remember {
                                    mutableStateOf(true)
                                }
                                var isNextButtonIsEnabled by remember {
                                    mutableStateOf(false)
                                }

                                var context = LocalContext.current


                                LazyColumn(
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                                ) {
                                    item {
                                        quizUiState.questions.get(quizUiState.questionIndex).answers.shuffled(
                                        )
                                            .forEachIndexed { index, info ->
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

                                                            }
                                                    ) {

                                                        Text(
                                                            text = info,
                                                            modifier = Modifier
                                                                .weight(0.9f)
                                                                .padding(start = 8.dp)
                                                        )
                                                        RadioButton(

                                                            // enabled = isRadioButtonIsEnabled,
                                                            selected = selectedOption == info,
                                                            onClick = {
//                                                                isRadioButtonIsEnabled = false
//                                                                isNextButtonIsEnabled = true
                                                                selectedOption = info
                                                                if (selectedOption == quizUiState.questions.get(
                                                                        quizUiState.questionIndex
                                                                    ).correct_answer
                                                                ) {
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Correct!!!",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                } else {

                                                                    Toast.makeText(
                                                                        context,
                                                                        "Wrong!!! the correct answer is ${
                                                                            quizUiState.questions.get(
                                                                                quizUiState.questionIndex
                                                                            ).correct_answer
                                                                        }",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()


                                                                }

                                                            },
                                                            modifier = Modifier.weight(0.1f)
                                                        )


                                                    }
                                                }

                                            }
                                        Spacer(modifier = Modifier.height(2.dp))

                                        Button(
                                            onClick = {
//                                                isRadioButtonIsEnabled = true
//                                                isNextButtonIsEnabled = false
                                                onNewEvent(DemoQuizUiEventClass.incrementQuestionIndex)

                                            },
                                            // enabled = isNextButtonIsEnabled
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


        }

    }
}







