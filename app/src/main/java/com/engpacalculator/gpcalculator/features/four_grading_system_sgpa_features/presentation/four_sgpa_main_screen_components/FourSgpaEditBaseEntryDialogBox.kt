package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream

@Composable
fun FourSgpaEditBaseEntryDialogBox(
    Desc: String,
    state: FourSgpaUiStates,
    events: (FourGpaUiEvents) -> Unit
) {
    val context = LocalContext.current
    Dialog(onDismissRequest = {
        events(FourGpaUiEvents.hideEditBaseEntryRegardlessDBox)

    }) {

        Card(

            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
            //.fillMaxHeight(0.4f)
            ,
            backgroundColor = Cream

        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
//                modifier = Modifier
//                    .fillMaxSize()
            ) {

                item {

                    Text(
                        modifier = Modifier.padding(
                            top = 4.dp,
                            start = 4.dp,
                            bottom = 8.dp

                        ),
                        text = Desc,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400
                    )



                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 8.dp),
                        contentAlignment = Alignment.BottomCenter,
                    ) {

                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            OutlinedTextField(
                                value = state.totalCourses,
                                onValueChange = {
                                    if (it.length <= state.maxNoOfCoursesLength) {
                                        events(FourGpaUiEvents.setTotalNumberOfEditedCourses(it))
                                        events(FourGpaUiEvents.setTotalCourses(it))
                                    } else {
                                        if (state.enteredCourses != "0") {
                                            events(
                                                FourGpaUiEvents.setTotalNumberOfEditedCourses(
                                                    state.enteredCourses
                                                )
                                            )
                                            events(FourGpaUiEvents.setTotalCourses(state.enteredCourses))

                                        } else if (state.enteredCourses == "0") {
                                            events(
                                                FourGpaUiEvents.setTotalNumberOfEditedCourses(
                                                    state.prevTotalNumberOfCourses
                                                )
                                            )
                                            events(FourGpaUiEvents.setTotalCourses(state.prevTotalNumberOfCourses))

                                        } else {
                                            events(
                                                FourGpaUiEvents.setTotalNumberOfEditedCourses(
                                                    state.totalCourses
                                                )
                                            )
                                            events(FourGpaUiEvents.setTotalCourses(state.totalCourses))
                                        }

                                        Toast.makeText(
                                            context, "cannot be more  than two digits",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
//                            events(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsETNOC)
                                },
                                label = {
                                    Text(text = state.defaultLabelETNOC)
                                },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedLabelColor = Color(state.defaultLabelColourETNOC),
                                    focusedBorderColor = Color(state.defaultLabelColourETNOC),
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.NumberPassword,
                                    imeAction = ImeAction.Next

                                ),
                                modifier = Modifier
                                    .padding(start = 4.dp, end = 4.dp)

                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center


                            ) {

                                Button(
//                                    modifier = Modifier
//                                        .padding(bottom = 8.dp)
                                    //colors = androidx.compose.material3.MaterialTheme.colorScheme,
                                    onClick = {
                                        events(FourGpaUiEvents.hideEditBaseEntryDBox)

                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = AppBars
                                    ),
                                ) {

                                    Text(text = "Done")

                                }

                            }

                        }
                    }

                }

            }
            // Spacer(modifier = Modifier.height(4.dp))


        }

    }


}