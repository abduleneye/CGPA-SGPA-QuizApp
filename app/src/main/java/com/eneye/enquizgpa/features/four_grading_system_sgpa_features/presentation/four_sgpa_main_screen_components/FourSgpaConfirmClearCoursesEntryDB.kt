package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
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
import com.eneye.enquizgpa.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.eneye.enquizgpa.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FourSgpaConfirmClearCoursesEntryConfirmationDialogBox(
    onEvent: (FourGpaUiEvents) -> Unit,
    dbState: FourSgpaUiStates,
    sheetState: BottomSheetState,


    ) {
    val scope = rememberCoroutineScope()


    Dialog(
        onDismissRequest = {
            onEvent(FourGpaUiEvents.hideClearConfirmationDBox)

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
            // .fillMaxHeight(0.3f)
            ,

            backgroundColor = Cream

        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
//                modifier = Modifier
//                    .fillMaxSize()
            ) {
                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Column {
                            Text(
                                text = "Are you sure you want to clear all " +
                                        "\nentered courses? " +
                                        "\nthis action can't be undone",
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(start = 16.dp, end = 4.dp),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))



                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp),
                        contentAlignment = Alignment.BottomCenter,

                        ) {


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 15.dp, bottom = 2.dp),
                            horizontalArrangement = Arrangement.End

                        ) {

                            Button(
                                onClick = {
                                    onEvent(FourGpaUiEvents.hideClearConfirmationDBox)

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

                                    onEvent(FourGpaUiEvents.resetTotalEntries)
                                    onEvent(FourGpaUiEvents.hideClearConfirmationDBox)


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

    }

}