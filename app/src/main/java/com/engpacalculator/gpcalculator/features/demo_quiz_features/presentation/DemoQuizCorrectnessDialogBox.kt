package com.engpacalculator.gpcalculator.features.demo_quiz_features.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DemoQuizCorrectnessDialogBox(
    onEvent: (DemoQuizUiEventClass) -> Unit,
    demoQuizUiState: DemoQuizUiState,

    ) {

    val scope = rememberCoroutineScope()


    Dialog(
        onDismissRequest = {
//            onEvent(FiveGpaUiEvents.hideClearConfirmationDBox)

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
                .fillMaxWidth(0.7f)
            // .fillMaxHeight(0.5f)
            ,

            backgroundColor = Cream

        ) {
            Column(
                modifier = Modifier
                // .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = demoQuizUiState.questionStatus,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                            //.padding(start = 16.dp)
                            ,

                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (demoQuizUiState.questionStatus == "wrong") {
                            Text(
                                text = "The Correct answer is ${
                                    demoQuizUiState.questions.get(
                                        demoQuizUiState.questionIndex
                                    ).correct_answer
                                }",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                //.padding(start = 16.dp)
                                ,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .paddingbottom = 0.dp),
//                    contentAlignment = Alignment.BottomCenter,
//
//                    ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 0.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.Center

                ) {

                    Button(
                        onClick = {
                            onEvent(DemoQuizUiEventClass.hideCorrectnessDialogBox)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppBars
                        ),
                    ) {

                        Text(text = "Ok")


                    }


                    //     }


                }

            }


        }


    }

}


