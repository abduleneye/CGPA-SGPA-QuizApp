package com.example.gpcalculator.ScreenElements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun CourseEntryDialogBox(

    onEvent: (DialogBoxUiEvents)-> Unit,
    dbState: DialogBoxState,
    title: String,
//    onDismiss: (DialogBoxUiEvents)-> Unit,
//    onPositiveButtonClicked: (DialogBoxUiEvents) -> Unit,
//    onNegativeButtonClicked: (DialogBoxUiEvents) -> Unit,
    properties: DialogProperties  = DialogProperties()
){
    
    Dialog(onDismissRequest = {
        onEvent(DialogBoxUiEvents.hideDataEntryDBox)
        onEvent(DialogBoxUiEvents.resetResultField)
                              }, properties = DialogProperties()) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = androidx.compose.ui.Modifier
                .height(420.dp)
        ) {

            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                       Text(text = title, modifier = androidx.compose.ui.Modifier
                           .align(Alignment.Start)
                           .padding(start = 20.dp, bottom = 10.dp))
                       Text(
                           modifier = androidx.compose.ui.Modifier
                               .align(Alignment.Start)
                               .padding(start = 20.dp, bottom = 10.dp),
                       text = "Entries: ${dbState.enteredCourses} of ${dbState.totalCourses}"
                       )


                    
                    OutlinedTextField(
                        value = dbState.courseCode,
                        onValueChange = {
                            onEvent(DialogBoxUiEvents.setCourseCode(it))
                                        },
                        label = {
                            Text(text = dbState.enteredCourseCodeLabel)
                        }
                    )

                    Spacer(modifier = androidx.compose.ui.Modifier
                        .height(10.dp))

                    Row {

                        DropDownMenu(
                            labelTextOne = dbState.pickedCourseUnitLabel,
                            labelTextTwo = dbState.pickedCourseGradeLabel,
                            dBState = dbState,
                            onEvent = onEvent,
                        )


                    }
                    
                    Spacer(modifier = androidx.compose.ui.Modifier
                        .height(130.dp))

                    Row(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp),
                        horizontalArrangement = Arrangement.End
                    ) {

                        TextButton(onClick = {
                            onEvent(DialogBoxUiEvents.hideDataEntryDBox)
                            onEvent(DialogBoxUiEvents.setSelectedCourseGrade(""))
                            onEvent(DialogBoxUiEvents.setSelectedCourseUnit(""))
                            onEvent(DialogBoxUiEvents.setCourseCode(""))


                        }) {
                            
                            Text(text = "Cancel")

                            

                        }

                        Spacer(modifier = androidx.compose.ui.Modifier
                            .width(5.dp))

                        Button(onClick = {
                            onEvent(DialogBoxUiEvents.addEntriesToArrayList)
                            //onEvent(DialogBoxUiEvents.setSelectedCourseGrade(""))
                            //onEvent(DialogBoxUiEvents.setSelectedCourseUnit(""))
                            //onEvent(DialogBoxUiEvents.setCourseCode(""))
                            //onEvent(DialogBoxUiEvents.hideDataEntryDBox)
                        }) {

                            Text(text = "Add")

                        }

                    }


                }



                }

            }

        }
        
    }

//@Composable
//fun ShowDialogBox(
//    state: DialogBoxState,
//    onEvent: (DialogBoxUiEvents) -> Unit
//
//){
//
//
//            CourseEntryDialogBox(
//                onEvent = onEvent,
//                dbState = state,
//                title = "Enter Your Course Details:",
//
//            )
//        }





