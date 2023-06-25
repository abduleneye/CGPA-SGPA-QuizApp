package com.example.gpcalculator.ScreenElements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay


@Composable
fun BaseEntryDialogBox(
    Desc: String,
    state: DialogBoxState,
    events: (DialogBoxUiEvents) -> Unit
){











    Dialog(onDismissRequest = { /*TODO*/ }) {



        val focusRequester = remember {
            FocusRequester()
        }

        LaunchedEffect(key1 = Unit){

            awaitFrame()
            focusRequester.requestFocus()
        }



        Card(

            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = androidx.compose.ui.Modifier
                .height(264.dp)
        ) {

            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(modifier = Modifier.padding(top = 16.dp),text = Desc, fontSize = 18.sp, fontWeight = FontWeight.W400)


                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = state.totalCourses,
                        onValueChange = {
                            events(DialogBoxUiEvents.setTotalCourses(it))
                        },
                        label = {
                            Text(text = state.totalCourseslabel)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .focusRequester(focusRequester)
                    )

                    Spacer(modifier = androidx.compose.ui.Modifier
                        .height(10.dp))

                    OutlinedTextField(
                        value = state.totalCreditLoad.toString(),

                        onValueChange = {
                            events(DialogBoxUiEvents.setTotalCreditLoad(it))

                        },

                        label = {
                            Text(text = state.totalCreditLoadLabel)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                        
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
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
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