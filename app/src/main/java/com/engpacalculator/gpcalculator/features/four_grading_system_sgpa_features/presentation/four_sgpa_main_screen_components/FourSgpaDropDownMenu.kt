package com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component

import GpCalculatorPrototype.Data.FourCourseDataEntries
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.Cream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FourSgpaDropDownMenu(
    labelTextOne: String,
    labelTextTwo: String,
    dBState: FourSgpaUiStates,
    onEvent: (FourGpaUiEvents) -> Unit,


    ) {

    val context = LocalContext.current

    var dataEntryObj = FourCourseDataEntries()

    var textFilledSize by remember {
        mutableStateOf(Size.Zero)
    }

    var iconOne = if (dBState.isUnitDropDownMenuExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    var iconTwo = if (dBState.isGradeDropDownMenuExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Row {

            Column {

                //val containerColor = FilledTextFieldTokens.ContainerColor.toColor()
                OutlinedTextField(


                    value = dBState.selectedCourseUnit,
                    textStyle = TextStyle(baselineShift = BaselineShift(-0.37f)),
                    onValueChange = {},
                    enabled = false,

                    modifier = Modifier
                        .onGloballyPositioned { co_ordinates ->

                            textFilledSize = co_ordinates.size.toSize()

                        }
                        .width(130.dp)
                        .height(70.dp),
                    colors = TextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledTrailingIconColor = Color.Black,
                        disabledContainerColor = Cream,
                        disabledPlaceholderColor = Color(dBState.defaultLabelColourCU),
                        unfocusedIndicatorColor = Color(dBState.defaultLabelColourCU),


                        //disabledIndicatorColor = Cream,

                    ),
                    label = {
                        Text(
                            text = labelTextOne,
                            style = TextStyle(baselineShift = BaselineShift(-0.37f))
                        )
                    },
                    trailingIcon = {

                        Icon(
                            imageVector = iconOne,
                            contentDescription = "DropDownIcon",
                            modifier = Modifier
                                .clickable {

                                    if (dBState.isUnitDropDownMenuExpanded) {
                                        onEvent(FourGpaUiEvents.hideUnitMenuDropDown)

                                    } else {
                                        onEvent(FourGpaUiEvents.showUnitMenuDropDown)


                                    }

                                }
                        )

                    }
                )

                DropdownMenu(
                    expanded = dBState.isUnitDropDownMenuExpanded,
                    onDismissRequest = {
                        onEvent(FourGpaUiEvents.hideUnitMenuDropDown)
                        onEvent(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCU)

                    },
                    modifier = Modifier
                        .width(with(LocalDensity.current) {
                            textFilledSize.width.toDp()
                        })
                        .height(120.dp)
                        .background(color = Cream),


                    ) {

                    dataEntryObj.unitList.forEach { courseUnit ->

                        DropdownMenuItem(
                            onClick = {
                                onEvent(FourGpaUiEvents.setSelectedCourseUnit(courseUnit))
                                onEvent(FourGpaUiEvents.hideUnitMenuDropDown)
                                onEvent(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCU)
                            },
                            modifier = Modifier
                                .padding(bottom = 0.3.dp),
                        ) {

                            Text(
                                text = courseUnit
                            )

                        }


                    }


                }

            }

            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )

            Column {

                OutlinedTextField(
                    value = dBState.selectedCourseGrade,
                    textStyle = TextStyle(baselineShift = BaselineShift(-0.37f)),
                    onValueChange = {},
                    enabled = false,

                    modifier = Modifier
                        .onGloballyPositioned { co_ordinates ->

                            textFilledSize = co_ordinates.size.toSize()

                        }
                        .width(130.dp)
                        .height(70.dp),
                    colors = TextFieldDefaults.colors(
                        //needed else where
                        //disabledTextColor = Color(dBState.defaultLabelColourCG),
                        //
                        disabledTextColor = Color.Black,
                        disabledTrailingIconColor = Color.Black,
                        disabledContainerColor = Cream,
                        disabledPlaceholderColor = Color(dBState.defaultLabelColourCG),
                        unfocusedIndicatorColor = Color(dBState.defaultLabelColourCG),
                        disabledPrefixColor = Color(dBState.defaultLabelColourCG),
                        disabledSuffixColor = Color(dBState.defaultLabelColourCG),


                        ),
                    //disabledIndicatorColor = Cream,

                    label = {
                        Text(
                            text = labelTextTwo,
                            style = TextStyle(baselineShift = BaselineShift(-0.37f))
                        )
                    },
                    trailingIcon = {

                        Icon(
                            imageVector = iconTwo,
                            contentDescription = "DropDownIcon",
                            modifier = Modifier
                                .clickable {

                                    if (dBState.isGradeDropDownMenuExpanded) {
                                        onEvent(FourGpaUiEvents.hideGradeMenuDropDown)

                                    } else {
                                        onEvent(FourGpaUiEvents.showGradeMenuDropDown)
                                    }

                                }
                        )

                    }
                )

                DropdownMenu(
                    expanded = dBState.isGradeDropDownMenuExpanded,
                    onDismissRequest = {
                        onEvent(FourGpaUiEvents.hideGradeMenuDropDown)
                        onEvent(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCG)

                    },
                    modifier = Modifier
                        .width(with(LocalDensity.current) {
                            textFilledSize.width.toDp()
                        })
                        .height(120.dp)
                        .background(color = Cream)
                ) {

                    dataEntryObj.gradeList.forEach { courseGrade ->

                        DropdownMenuItem(onClick = {
                            onEvent(FourGpaUiEvents.setSelectedCourseGrade(courseGrade))
                            onEvent(FourGpaUiEvents.hideGradeMenuDropDown)
                            onEvent(FourGpaUiEvents.resetBackToDefaultValuesFromErrorsCG)

                        }) {

                            Text(text = courseGrade)

                        }

                    }

                }

            }


        }


    }


}