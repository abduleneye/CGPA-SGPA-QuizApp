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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.gpcalculator.presentation.myViewModels.course_list_screen_component.DropDownMenu
import com.example.gpcalculator.ui.theme.Cream


@Composable
fun CourseEntryDialogBox(

    onEvent: (DialogBoxUiEvents) -> Unit,
    dbState: DialogBoxState,
    title: String,
    properties: DialogProperties = DialogProperties()
) {
    var scrollState = rememberScrollState()
    val context = LocalContext.current



    Dialog(
        onDismissRequest = {
            onEvent(DialogBoxUiEvents.hideDataEntryDBox)
            onEvent(DialogBoxUiEvents.resetResultField)
            onEvent(DialogBoxUiEvents.resetAlreadyInList)
            onEvent(DialogBoxUiEvents.setSelectedCourseGrade(""))
            onEvent(DialogBoxUiEvents.setSelectedCourseUnit(""))
            onEvent(DialogBoxUiEvents.setCourseCode(""))

        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.SecureOn,
        )
    ) {


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
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)

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

                        Text(
                            text = title, modifier = androidx.compose.ui.Modifier
                                .align(Alignment.Start)
                                .padding(start = 20.dp)
                        )
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
                                Text(text = dbState.defaultEnteredCourseCodeLabel)
                            }
                        )

                        Spacer(
                            modifier = Modifier
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

                        Spacer(
                            modifier = androidx.compose.ui.Modifier
                                .height(
                                    //20.dp
                                    126.dp //final
                                )
                        )

                        Row(
                            modifier = androidx.compose.ui.Modifier
                                .fillMaxWidth()
                                .padding(end = 15.dp),
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

                            Spacer(
                                modifier = androidx.compose.ui.Modifier
                                    .width(16.dp)
                            )

                            Button(onClick = {

                                onEvent(DialogBoxUiEvents.addEntriesToArrayList)
                                if (
                                    dbState.allReadyInList
                                ) {
                                    Toast.makeText(
                                        context,
                                        "entries already in list",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

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

}

