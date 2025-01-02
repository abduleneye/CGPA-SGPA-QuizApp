package com.eneye.enquizgpa.features.demo_quiz_features.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuizIntroDialogBoxFromViewModel(
    demoQuizOnEvent: (DemoQuizUiEventClass) -> Unit,
    quizIntroDBState: DemoQuizUiState,


    ) {


    Dialog(
        onDismissRequest = {
            demoQuizOnEvent(DemoQuizUiEventClass.hideIntroDialogBoxVisibilty)

        },
//        properties = DialogProperties(
//            dismissOnBackPress = true,
//            dismissOnClickOutside = true,
//            //securePolicy = SecureFlagPolicy.SecureOn,
//        )
    ) {


        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(0.8f),

            backgroundColor = Cream

        ) {
            Column(
                //horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {


                LazyColumn(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f)
                ) {

                    item {
                        Text(
                            text = "Welcome! to the quiz section of " +
                                    "this app, " +
                                    "you can participate and stand a " +
                                    "chance to win  " +
                                    "airtime or data give away  at the " +
                                    "end of every week, " +
                                    "if you happen to have the highest score " +
                                    "in any of the " +
                                    "categories " +
                                    "along side  expanding and testing " +
                                    "your knowledge base " +
                                    "on a variety of topics, " +
                                    "Although it's only the demo mode that " +
                                    "is active for now " +
                                    "which would have repetition of " +
                                    "questions " +
                                    "since it's just the demo mode, " +
                                    "once the rewarding mode is active which " +
                                    "will definitely take sometime due to complexities. " +
                                    "hopefully if all goes well you will be " +
                                    "notified with a push notification to " +
                                    "update your app " +
                                    "directly from the home screen of this " +
                                    "app or from amazon app store " +
                                    "for now just play and enjoy!!! ",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 16.dp, end = 8.dp)
                                .weight(1f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 29.sp
                        )
                    }

                }


                Spacer(modifier = Modifier.height(0.dp))



                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.fillMaxHeight()
                        .padding(bottom = 24.dp),
                    contentAlignment = Alignment.BottomCenter,

                    ) {


                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        // .padding(end = 15.dp, bottom = 2.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally


                    ) {

                        Button(
                            onClick = {
                                demoQuizOnEvent(DemoQuizUiEventClass.hideIntroDialogBoxVisibilty)

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppBars
                            ),
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {

                            Text(text = "Ok, I understand")


                        }
//
//                        Spacer(
//                            modifier = Modifier
//                                .width(16.dp)
//                        )


                    }


                }

            }


        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun QuizIntroDialogBoxFromViewModel(

) {
    //QuizIntroDialogBox()
    Text(text = "Hello", modifier = Modifier.fillMaxSize())
}