package com.example.gpcalculator.ScreenElements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun BaseEntryDialogBox(
    Desc: String,
    state: DialogBoxState,
    events: (DialogBoxUiEvents) -> Unit
){
    
    Dialog(onDismissRequest = { /*TODO*/ }) {

        Card(

            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = androidx.compose.ui.Modifier
                .height(220.dp)
        ) {

            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = Desc)

                    OutlinedTextField(
                        value = state.totalCourses,
                        onValueChange = {
                            events(DialogBoxUiEvents.setTotalCourses(it))
                        },
                        label = {
                            Text(text = state.totalCourseslabel)
                        }
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
                        
                    )

                    Row(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center


                    ) {

                        Button(onClick = {
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