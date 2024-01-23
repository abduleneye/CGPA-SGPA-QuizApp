package com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.four_sgpa_main_screen_components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.android.awaitFrame


@Composable
fun FourSgpaBaseEntryDialogBox(
    Desc: String,
    state: FourSgpaUiStates,
    events: (FourGpaUiEvents) -> Unit
) {
    val context = LocalContext.current


    Dialog(onDismissRequest = {
        events(FourGpaUiEvents.hideBaseEntryRegardlessDBox)
        events(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC)

    }) {


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
                .height(220.dp)
                .padding(start = 8.dp, end = 8.dp)
            //.padding(top = 16.dp)
            ,
            backgroundColor = Cream

        ) {


            Column {
                Text(
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 22.dp
                    ),
                    text = state.greeting + ",",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                )

                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    modifier = Modifier.padding(
                        //top = 16.dp,
                        start = 22.dp
                    ),
                    text = Desc,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                )
            }
            Spacer(modifier = Modifier.height(16.dp))



            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter,
            ) {


                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = state.totalCourses,
                        onValueChange = {

                            if (it.length <= state.maxNoOfCoursesLength) {
                                events(FourGpaUiEvents.setPrevTotalCourses(it))
                                events(FourGpaUiEvents.setTotalCourses(it))
                            } else {

                                events(FourGpaUiEvents.setPrevTotalCourses(""))
                                events(FourGpaUiEvents.setTotalCourses(""))
                                Toast.makeText(
                                    context,
                                    "cannot be more  than two digits",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            events(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsTNOC)
                        },
                        label = {
                            Text(text = state.defaultLabelTNOC)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color(state.defaultLabelColourTNOC),
                            focusedBorderColor = Color(state.defaultLabelColourTNOC)
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Done

                        ),
                        modifier = Modifier
                            .focusRequester(focusRequester)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center


                    ) {

                        Button(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            //colors = androidx.compose.material3.MaterialTheme.colorScheme,
                            onClick = {
                                events(FourGpaUiEvents.hideBaseEntryDBox)
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

        }

    }


}
