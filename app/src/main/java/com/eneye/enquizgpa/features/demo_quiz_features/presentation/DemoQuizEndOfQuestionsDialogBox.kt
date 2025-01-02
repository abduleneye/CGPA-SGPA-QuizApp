package com.eneye.enquizgpa.features.demo_quiz_features.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DemoQuizEndOfQuestionsDialogBox(
    onEvent: (DemoQuizUiEventClass) -> Unit,
    demoQuizUiState: DemoQuizUiState,
    navController: NavController?,


    ) {

    val scope = rememberCoroutineScope()


    Dialog(
        onDismissRequest = {
            // onEvent(DemoQuizUiEventClass.hideEndOfQuestionsDialogBox)

        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            //securePolicy = SecureFlagPolicy.SecureOn,
        )
    ) {


        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
            //.fillMaxHeight(0.5f)
            ,

            backgroundColor = Cream

        ) {
            Column {


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Column {
                        Text(
                            text = "You have answered all questions",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400
                        )
                        Text(
                            text = "You scored ${demoQuizUiState.currentScore} out of ${demoQuizUiState.questions.size}",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400
                        )
                        Text(
                            text = "Do you want to retake the quiz?",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400
                        )

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))





                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.End

                ) {

                    Button(
                        onClick = {
                            if (navController != null) {
                                navController.popBackStack()
                            }
                            onEvent(DemoQuizUiEventClass.hideEndOfQuestionsDialogBox)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppBars
                        ),
                    ) {

                        Text(text = "No")


                    }

                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )

                    Button(
                        onClick = {

                            onEvent(
                                DemoQuizUiEventClass.loadData(
                                    category = demoQuizUiState.questionCategory,
                                    amount = demoQuizUiState.amountOfQuestions
                                )
                            )
                            onEvent(DemoQuizUiEventClass.hideEndOfQuestionsDialogBox)


                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppBars
                        ),
                    ) {

                        Text(text = "Yes")

                    }


                }


            }

        }


    }

}


