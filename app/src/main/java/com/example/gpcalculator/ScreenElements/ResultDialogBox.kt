package com.example.gpcalculator.ScreenElements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ResultDialogBox(
    desc: String,
    state: DialogBoxState,
    events: (DialogBoxUiEvents) -> Unit
){

    Dialog(onDismissRequest = {
        events(DialogBoxUiEvents.hideResultDBox)
        //events(DialogBoxUiEvents.)
    }) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = androidx.compose.ui.Modifier
                .height(100.dp)
        ) {

            Box(modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center){

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Your Gp is:")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text =
                    state.finalResult
                    //"${state.totalCourses}"
                    )


                }


            }

        }

    }


}