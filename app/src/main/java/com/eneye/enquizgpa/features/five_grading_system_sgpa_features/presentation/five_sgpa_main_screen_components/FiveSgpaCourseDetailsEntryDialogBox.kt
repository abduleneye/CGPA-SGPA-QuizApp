package com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.eneye.enquizgpa.presentation.myViewModels.course_list_screen_component.FiveSgpaDropDownMenu
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream


@Composable
fun FiveSgpaCourseDetailsEntryDialogBox(

    onEvent: (FiveGpaUiEvents) -> Unit,
    dbState: FiveSgpaUiStates,
    title: String,
    properties: DialogProperties = DialogProperties(),
) {
    val context = LocalContext.current



    Dialog(
        onDismissRequest = {
            onEvent(FiveGpaUiEvents.hideDataEntryDBox)
            onEvent(FiveGpaUiEvents.resetResultField)
            onEvent(FiveGpaUiEvents.resetAlreadyInList)
            onEvent(FiveGpaUiEvents.setSelectedCourseGrade(""))
            onEvent(FiveGpaUiEvents.setSelectedCourseUnit(""))
            onEvent(FiveGpaUiEvents.setCourseCode(""))
            onEvent(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCC)
            onEvent(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCU)
            onEvent(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCG)


        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            //securePolicy = SecureFlagPolicy.SecureOn,
        )
    ) {


        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
            //.fillMaxHeight(0.6f)
            ,

            backgroundColor = Cream

        ) {
            LazyColumn(
//                modifier = Modifier
//                    .verticalScroll(scrollState)
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                //  .fillMaxSize()

            ) {

                item {
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
                                text = title, modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(start = 4.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(start = 4.dp),
                                text = "Entries: ${dbState.enteredCourses} of ${dbState.totalCourses}"
                            )



                            OutlinedTextField(
                                value = dbState.courseCode,
                                onValueChange = {
                                    onEvent(FiveGpaUiEvents.setCourseCode(it))
                                    onEvent(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCC)
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedLabelColor = Color(dbState.defaultLabelColourCC),
                                    focusedBorderColor = Color(dbState.defaultLabelColourCC),
                                    unfocusedBorderColor = Color(dbState.defaultLabelColourCC)
                                ),
                                label = {
                                    Text(text = dbState.defaultEnteredCourseCodeLabel)
                                },
                                singleLine = true,

                                )

                            Spacer(
                                modifier = Modifier
                                    .height(8.dp)
                            )

                            Row {

                                FiveSgpaDropDownMenu(
                                    labelTextOne = dbState.pickedCourseUnitDefaultLabel,
                                    labelTextTwo = dbState.pickedCourseGradeDefaultLabel,
                                    dBState = dbState,
                                    onEvent = onEvent,
                                )


                            }

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        //20.dp
                                        126.dp //final
                                    )
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 15.dp, bottom = 4.dp),
                                horizontalArrangement = Arrangement.End
                            ) {

                                Button(
                                    onClick = {
                                        onEvent(FiveGpaUiEvents.hideDataEntryDBox)
                                        onEvent(FiveGpaUiEvents.setSelectedCourseGrade(""))
                                        onEvent(FiveGpaUiEvents.setSelectedCourseUnit(""))
                                        onEvent(FiveGpaUiEvents.setCourseCode(""))
                                        onEvent(FiveGpaUiEvents.resetBackToDefaultValuesFromErrorsCC)


                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = AppBars
                                    ),
                                ) {

                                    Text(text = "Cancel")


                                }

                                Spacer(
                                    modifier = Modifier
                                        .width(16.dp)
                                )

                                Button(
                                    onClick = {

                                        onEvent(FiveGpaUiEvents.addEntriesToArrayList)
                                        if (dbState.allReadyInList) {
                                            Toast.makeText(
                                                context,
                                                "${dbState.matchAlreadyInCourseEntry} already in list",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                        //onEvent(FiveGpaUiEvents.setSelectedCourseGrade(""))
                                        //onEvent(FiveGpaUiEvents.setSelectedCourseUnit(""))
                                        //onEvent(FiveGpaUiEvents.setCourseCode(""))
                                        //onEvent(FiveGpaUiEvents.hideDataEntryDBox)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = AppBars
                                    ),


                                    ) {

                                    Text(text = "Add")

                                }

                            }


                        }


                    }

                }

            }


        }

    }

}

