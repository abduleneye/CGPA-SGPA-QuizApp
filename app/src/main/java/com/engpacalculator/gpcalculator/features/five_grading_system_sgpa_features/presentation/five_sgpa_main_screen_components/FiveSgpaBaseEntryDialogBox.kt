package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.android.awaitFrame


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiveSgpaBaseEntryDialogBox(
    Desc: String,
    state: FiveSgpaUiStates,
    events: (FiveGpaUiEvents) -> Unit
) {
    val context = LocalContext.current


    Dialog(
        onDismissRequest = {
            events(FiveGpaUiEvents.hideBaseEntryRegardlessDBox)
            events(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC)

        },
        properties = DialogProperties(
        )
    ) {


        val focusRequester = remember {
            FocusRequester()
        }

        LaunchedEffect(key1 = Unit) {

            awaitFrame()
            focusRequester.requestFocus()
        }



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
                modifier = Modifier
                //.fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier.padding(bottom = 8.dp, top = 4.dp, start = 4.dp)
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.padding(
                                    // start = 4.dp
                                ),
                                text = state.greeting + ",",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            )
                            //Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                modifier = Modifier.padding(
                                    //top = 16.dp,
                                    //start = 4.dp
                                ),
                                text = Desc,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            )
                        }

                    }

                    // Spacer(modifier = Modifier.height(8.dp))


                    OutlinedTextField(
                        value = state.totalCourses,
                        onValueChange = {

                            if (it.length <= state.maxNoOfCoursesLength) {
                                events(FiveGpaUiEvents.setPrevTotalCourses(it))
                                events(FiveGpaUiEvents.setTotalCourses(it))
                            } else {

                                events(FiveGpaUiEvents.setPrevTotalCourses(""))
                                events(FiveGpaUiEvents.setTotalCourses(""))
                                Toast.makeText(
                                    context,
                                    "cannot be more  than two digits",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            events(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC)
                        },
                        label = {
                            Text(text = state.defaultLabelTNOC, fontSize = 12.sp)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(state.defaultLabelColourTNOC),
                            focusedLabelColor = Color(state.defaultLabelColourTNOC),
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Done

                        ),
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .padding(start = 4.dp, end = 4.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        horizontalArrangement = Arrangement.Center


                    ) {

                        Button(
                            modifier = Modifier,
                            //.padding(bottom = 4.dp)
                            //colors = androidx.compose.material3.MaterialTheme.colorScheme,
                            onClick = {
                                events(FiveGpaUiEvents.hideBaseEntryDBox)

//                                Toast.makeText(
//                                    context,
//                                    "${state.totalCourses} : ${state.prevTotalNumberOfCourses}",
//                                    Toast.LENGTH_LONG
//                                ).show()
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
            //Spacer(modifier = Modifier.height(16.dp))


        }


    }


}


