package com.example.gpcalculator.presentation.course_list_screen_components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gpcalculator.ui.theme.AppBars
import com.example.gpcalculator.ui.theme.Cream

@Composable
fun EditBaseEntryDialogBox(
    Desc: String,
    state: DialogBoxState,
    events: (DialogBoxUiEvents) -> Unit
) {

    val context = LocalContext.current
    var viewModel = viewModel<gpcalculator_view_model>()
    val state by viewModel.dbState.collectAsState()
    val statetwo by viewModel.courseEntries.collectAsState()
    var dummy by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = {
        events(DialogBoxUiEvents.hideEditBaseEntryRegardlessDBox)
    }) {

        Card(

            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(200.dp),
            backgroundColor = Cream

        ) {
            Column {
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 22.dp

                    ),
                    text = Desc,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                )


            }
            // Spacer(modifier = Modifier.height(4.dp))


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
                        value = state.totalCourses.toString(),
                        onValueChange = { it ->
                            events(DialogBoxUiEvents.setTotalCourses(it))
                            events(DialogBoxUiEvents.resetDefaultValuesFromErrorsTNOC)
                            events(DialogBoxUiEvents.resetDefaultLabelTextTNOC)
                            dummy = it
                            Toast.makeText(
                                context,
                                "${state.totalCourses}:${state.prevTotalNumberOfCourses}:${dummy}",
                                Toast.LENGTH_SHORT
                            ).show()

                        },
                        label = {
                            Text(text = state.defaultLabelETNOC)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = AppBars,
                            focusedBorderColor = AppBars
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Next

                        ),

                        )

//                    Spacer(
//                        modifier = Modifier
//                            .height(10.dp)
//                    )

//                    OutlinedTextField(
//                        value = state.totalCreditLoad,
//
//                        onValueChange = {
//                            events(DialogBoxUiEvents.setTotalCreditLoad(it))
//                            events(DialogBoxUiEvents.resetDefaultValuesFromErrorsTNOCL)
//                            events(DialogBoxUiEvents.resetDefaultLabelTextTNOCL)
//
//
//                        },
//
//                        label = {
//                            Text(text = state.defaultLabelETNOCL)
//                        },
//                        singleLine = true,
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedLabelColor = state.defaultLabelColourETNOCL,
//                            focusedBorderColor = state.defaultLabelColourETNOCL
//                        ),
//
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.NumberPassword,
//                            imeAction = ImeAction.Done
//                        ),
//
//
//                        )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center


                    ) {

                        Button(
                            modifier = Modifier
                                .padding(bottom = 8.dp), //colors = androidx.compose.material3.MaterialTheme.colorScheme,
                            onClick = {
                                if (state.totalCourses.isNotBlank()) {
                                    if (
                                        state.totalCourses.toInt() < state.enteredCourses.toInt() || state.totalCourses.equals(
                                            "0"
                                        )
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "entry can't be 0 or less then entered courses....${state.totalCourses}:${state.prevTotalNumberOfCourses}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        events(DialogBoxUiEvents.setTotalCourses(state.prevTotalNumberOfCourses))

                                    }


                                } else if (state.totalCourses == "") {
                                    events(DialogBoxUiEvents.setTotalCourses(state.prevTotalNumberOfCourses))
                                    Toast.makeText(
                                        context,
                                        "entry can't be empty..${state.totalCourses}:${state.prevTotalNumberOfCourses}:${dummy}",
                                        Toast.LENGTH_LONG
                                    ).show()


                                } else if (dummy.toInt() == state.prevTotalNumberOfCourses.toInt() || dummy.toInt() > state.prevTotalNumberOfCourses.toInt()) {
                                    events(DialogBoxUiEvents.hideEditBaseEntryDBox)
                                    Toast.makeText(
                                        context,
                                        "equal ${state.totalCourses}::${state.prevTotalNumberOfCourses}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }


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