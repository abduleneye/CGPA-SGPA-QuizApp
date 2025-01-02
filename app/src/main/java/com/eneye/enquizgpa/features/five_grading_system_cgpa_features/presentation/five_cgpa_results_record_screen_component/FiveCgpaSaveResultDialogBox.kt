package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.BottomSheetState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FiveCgpaSaveResultDialogBox(
    onEvent: (FiveGpaUiEvents) -> Unit,
    fiveCgpaUiStates: FiveCgpaUiStates,
    sheetState: BottomSheetState,
    mFirebaseAnalytics: FirebaseAnalytics,


    ) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Dialog(
        onDismissRequest = {
            onEvent(FiveGpaUiEvents.resetBackToDefaultValueFromErrorSRAFiveCgpa)
            onEvent(FiveGpaUiEvents.hideFiveCgpaSaveResultDB)
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
                .fillMaxWidth(0.9f),

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
                            text = "Save result As:",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 20.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, end = 8.dp),
                    value = fiveCgpaUiStates.saveResultAs,
                    onValueChange = {

                        onEvent(FiveGpaUiEvents.setFiveCgpaSRA(it))
                        onEvent(FiveGpaUiEvents.resetBackToDefaultValueFromErrorSRAFiveCgpa)


                    },
                    label = {
                        Text(text = fiveCgpaUiStates.defaultLabelSRA)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = Color(fiveCgpaUiStates.defaultLabelColourSRA),
                        focusedBorderColor = Color(fiveCgpaUiStates.defaultLabelColourSRA),
                    ),


                    )


                Spacer(modifier = Modifier.height(8.dp))



                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    contentAlignment = Alignment.BottomCenter,

                    ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp, bottom = 2.dp, top = 8.dp),
                        horizontalArrangement = Arrangement.End

                    ) {

                        Button(
                            onClick = {
                                onEvent(FiveGpaUiEvents.hideFiveCgpaSaveResultDB)
                                onEvent(FiveGpaUiEvents.resetBackToDefaultValueFromErrorSRAFiveCgpa)

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppBars
                            ),
                        ) {

                            Text(text = "Cancel")


                        }

                        Spacer(
                            modifier = Modifier
                                .width(16.dp)
                        )

                        Button(
                            onClick = {

                                val params = Bundle()
                                params.putString(
                                    "FiveCgpaSaveResultButton",
                                    "FiveCgpaSaveResultButtonClicked"
                                )
                                mFirebaseAnalytics.logEvent("FiveCgpaSaveResultButton", params)

                                onEvent(FiveGpaUiEvents.saveFiveCgpaResult)
                                // onEvent(FiveGpaUiEvents.hideFiveCgpaSaveResultDB)

                                if (fiveCgpaUiStates.saveResultAs.isNotEmpty()) {

                                    Toast.makeText(
                                        context,
                                        "${fiveCgpaUiStates.saveResultAs} saved in records successfully!!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                //Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show()
                                //onEvent(FiveGpaUiEvents.hideFiveSgpaSaveResultDB)


                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppBars
                            ),
                        ) {

                            Text(text = "Save")

                        }


                    }


                }

            }


        }

    }


}