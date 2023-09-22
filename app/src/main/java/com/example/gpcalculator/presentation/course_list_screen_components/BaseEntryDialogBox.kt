package com.example.gpcalculator.presentation.course_list_screen_components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.android.awaitFrame


@Composable
fun BaseEntryDialogBox(
    Desc: String,
    state: DialogBoxState,
    events: (DialogBoxUiEvents) -> Unit
) {

    Dialog(onDismissRequest = { /*TODO*/ }) {


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
            modifier = androidx.compose.ui.Modifier
                .height(264.dp),
            backgroundColor = Cream

        ) {

            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = Desc,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400
                    )


                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = state.totalCourses,
                        onValueChange = {
                            events(DialogBoxUiEvents.setTotalCourses(it))
                            events(DialogBoxUiEvents.resetDefaultValuesFromErrorsTNOC)
                            events(DialogBoxUiEvents.resetDefaultLabelTextTNOC)
                        },
                        label = {
                            Text(text = state.defaultLabelTNOC)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = state.defaultLabelColourTNOC,
                            focusedBorderColor = state.defaultLabelColourTNOC
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Next

                        ),
                        modifier = Modifier
                            .focusRequester(focusRequester)
                    )

                    Spacer(
                        modifier = androidx.compose.ui.Modifier
                            .height(10.dp)
                    )

                    OutlinedTextField(
                        value = state.totalCreditLoad,

                        onValueChange = {
                            events(DialogBoxUiEvents.setTotalCreditLoad(it))
                            events(DialogBoxUiEvents.resetDefaultValuesFromErrorsTNOCL)
                            events(DialogBoxUiEvents.resetDefaultLabelTextTNOCL)


                        },

                        label = {
                            Text(text = state.defaultLabelTNOCL)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = state.defaultLabelColourTNOCL,
                            focusedBorderColor = state.defaultLabelColourTNOCL
                        ),

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Done
                        ),


                        )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center


                    ) {

                        Button(
                            modifier = Modifier
                                .padding(bottom = 10.dp),
                            //colors = androidx.compose.material3.MaterialTheme.colorScheme,
                            onClick = {
                                events(DialogBoxUiEvents.hideBaseEntryDBox)
                            }) {

                            Text(text = "Done")

                        }

                    }

                }
            }

        }

    }


}