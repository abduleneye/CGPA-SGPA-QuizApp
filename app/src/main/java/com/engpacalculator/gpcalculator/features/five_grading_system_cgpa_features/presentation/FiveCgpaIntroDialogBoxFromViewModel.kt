package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FiveCgpaIntroDialogBoxFromViewModel(
    fiveCgpaUiEvents: (FiveGpaUiEvents) -> Unit,
    fiveCgpaUiStates: FiveCgpaUiStates,


    ) {

    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            // fiveCgpaUiEvents(FiveGpaUiEvents.hideFiveCgpaIntroDialogBox)

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
                            text = "Welcome! to the 5.0 CGPA " +
                                    "section of this app " +
                                    "it completely relies on your previously calculated and saved " +
                                    "5.0 SGPA(semesterial GPA) results. " +
                                    "in order to calculate your CGPA you need to " +
                                    "have at least two(2) of your current level SGPA " +
                                    "or previous level SGPA  result saved since it's cumulative. " +
                                    "if you haven't saved any please go back and do so " +
                                    "if you have done so you can simply select the " +
                                    "SGPA results you want to calculate your CGPA for by checking the boxes " +
                                    "and clicking the mark button below to calculate you can also save the CGPA " +
                                    "result if you wish to.",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 24.dp, end = 8.dp)
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
                        //  .fillMaxHeight()
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
                                fiveCgpaUiEvents(FiveGpaUiEvents.hideFiveCgpaIntroDialogBox)

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppBars
                            ),
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
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