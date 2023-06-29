package com.example.gpcalculator.ScreenElements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.gpcalculator.ui.theme.Cream


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
                              }, properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        securePolicy = SecureFlagPolicy.SecureOn
    )) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = androidx.compose.ui.Modifier
                .height(
                    390.dp //final
                    //300.dp
                    //dbState.dialogDefaultHeight.value.dp
                ),
            backgroundColor = Cream

        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.TopStart,

            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                       Text(text = title, modifier = androidx.compose.ui.Modifier
                           .align(Alignment.Start)
                           .padding(start = 20.dp))
                    Spacer(modifier = Modifier.height(2.dp))
                       Text(
                           modifier = androidx.compose.ui.Modifier
                               .align(Alignment.Start)
                               .padding(start = 20.dp),
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

                    Spacer(modifier = Modifier
                        .height(8.dp)
                    )

                    Row {

                        DropDownMenu(
                            labelTextOne = dbState.pickedCourseUnitLabel,
                            labelTextTwo = dbState.pickedCourseGradeLabel,
                            dBState = dbState,
                            onEvent = onEvent,
                        )


                    }
                    
                    Spacer(modifier = androidx.compose.ui.Modifier
                        .height(
                            //20.dp
                            126.dp //final
                        ))

                    Row(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp)
                        ,
                        horizontalArrangement = Arrangement.End
                    ) {

                        Button(onClick = {
                            onEvent(DialogBoxUiEvents.hideDataEntryDBox)
                            onEvent(DialogBoxUiEvents.setSelectedCourseGrade(""))
                            onEvent(DialogBoxUiEvents.setSelectedCourseUnit(""))
                            onEvent(DialogBoxUiEvents.setCourseCode(""))


                        }) {
                            
                            Text(text = "Cancel")

                            

                        }

                        Spacer(modifier = androidx.compose.ui.Modifier
                            .width(16.dp))

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





