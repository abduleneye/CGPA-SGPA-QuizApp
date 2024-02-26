package com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream


@Composable
fun FourSgpaEditCourseEntryDialogBox(

    onEvent: (FourGpaUiEvents) -> Unit,
    dbState: FourSgpaUiStates,
    title: String,
    properties: DialogProperties = DialogProperties()
) {
    var scrollState = rememberScrollState()
    var context = LocalContext.current


    Dialog(
        onDismissRequest = {
            onEvent(FourGpaUiEvents.hideCourseEntryEditDBox)
            onEvent(FourGpaUiEvents.resetResultField)
            onEvent(FourGpaUiEvents.setSelectedCourseGrade(""))
            onEvent(FourGpaUiEvents.setSelectedCourseUnit(""))
            onEvent(FourGpaUiEvents.setCourseCode(""))
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
                .fillMaxHeight(0.6f),
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
                            text = title,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(start = 20.dp)

                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 20.dp),
                            text = "Entry: ${(dbState.courseEntryIndex.toInt() + 1)} of ${dbState.totalCourses}"
                        )



                        OutlinedTextField(
                            value = dbState.courseCode,
                            onValueChange = {
                                onEvent(FourGpaUiEvents.setCourseCode(it))



                                onEvent(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsECC)
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = Color(dbState.defaultLabelColourECC),
                                focusedBorderColor = Color(dbState.defaultLabelColourECC)
                            ),
                            label = {
                                Text(text = dbState.defaultEditCourseCodeLabel)
                            }
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )

                        Row {

                            FourSgpaDropDownMenu(
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
                                .padding(end = 15.dp),
                            horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    onEvent(FourGpaUiEvents.hideCourseEntryEditDBox)
                                    onEvent(FourGpaUiEvents.setSelectedCourseGrade(""))
                                    onEvent(FourGpaUiEvents.setSelectedCourseUnit(""))
                                    onEvent(FourGpaUiEvents.setCourseCode(""))


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
                                    onEvent(FourGpaUiEvents.replaceEditedInEntriesToArrayList)
//                                    if (dbState.allReadyInListForEditCourseEntries) {
//                                        Toast.makeText(
//                                            context,
//                                            "entries already in list no changes made",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }


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

}
